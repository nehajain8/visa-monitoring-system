import { Stack, StackProps, Duration } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as rds from 'aws-cdk-lib/aws-rds';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as events from 'aws-cdk-lib/aws-events';
import * as targets from 'aws-cdk-lib/aws-events-targets';

export class VisaMonitoringStack extends Stack {
  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    // VPC
    const vpc = new ec2.Vpc(this, 'VisaVpc', {});

    // RDS PostgreSQL
    const db = new rds.DatabaseInstance(this, 'VisaDb', {
      engine: rds.DatabaseInstanceEngine.postgres({ version: rds.PostgresEngineVersion.VER_15 }),
      instanceType: ec2.InstanceType.of(ec2.InstanceClass.T3, ec2.InstanceSize.MICRO),
      vpc,
      allocatedStorage: 20,
      publiclyAccessible: true,
      credentials: rds.Credentials.fromGeneratedSecret('visa_admin')
    });

    // S3 Bucket for reports
    const bucket = new s3.Bucket(this, 'OverstayerReports', {
      lifecycleRules: [{ expiration: Duration.days(365) }]
    });

    // Lambda for nightly job
    const nightlyFn = new lambda.Function(this, 'NightlyFn', {
      runtime: lambda.Runtime.PYTHON_3_12,
      code: lambda.Code.fromAsset('../nightly-job'),
      handler: 'lambda_function.handler',
      memorySize: 512,
      timeout: Duration.minutes(1),
      environment: {
        BUCKET: bucket.bucketName,
        DB_ENDPOINT: db.dbInstanceEndpointAddress,
        DB_PORT: db.dbInstanceEndpointPort
      }
    });

    bucket.grantWrite(nightlyFn);
    db.connections.allowFrom(nightlyFn, ec2.Port.tcp(5432));

    new events.Rule(this, 'NightlySchedule', {
      schedule: events.Schedule.cron({ minute: '0', hour: '1' }),
      targets: [new targets.LambdaFunction(nightlyFn)]
    });
  }
}
