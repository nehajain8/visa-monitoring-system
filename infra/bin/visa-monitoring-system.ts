#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { VisaMonitoringStack } from '../lib/visa-monitoring-system-stack';

const app = new cdk.App();
new VisaMonitoringStack(app, 'VisaMonitoringStack', {
  /* If you don't specify 'env', this stack will be environment-agnostic. */
});
