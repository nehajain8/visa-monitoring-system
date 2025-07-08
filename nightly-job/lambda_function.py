import os, csv, psycopg2, datetime, boto3

def handler(event, context):
    today = datetime.date.today()
    bucket = os.environ['BUCKET']
    conn = psycopg2.connect(
        host=os.environ['DB_ENDPOINT'],
        port=os.environ['DB_PORT'],
        database='visa_db',
        user='visa_admin',
        password=os.environ.get('DB_PASSWORD', '')
    )

    cur = conn.cursor()
    query = """
    WITH last_mov AS (
      SELECT passport_no,
             visa_id,
             MAX(CASE WHEN movement_type='ENTRY' THEN movement_ts END) AS last_entry,
             MAX(CASE WHEN movement_type='EXIT'  THEN movement_ts END) AS last_exit
      FROM movement
      GROUP BY passport_no, visa_id
    )
    SELECT passport_no, visa_id, expiry_date,
           last_entry, last_exit
    FROM last_mov
    JOIN visa USING (passport_no, visa_id)
    WHERE expiry_date = %s;
    """
    cur.execute(query, (today,))
    rows = cur.fetchall()
    illegal = []
    for (passport_no, visa_id, expiry_date, last_entry, last_exit) in rows:
        if last_exit and last_exit.date() < expiry_date:
            continue
        if (not last_exit) or (last_entry and last_entry > last_exit):
            illegal.append([passport_no, visa_id, expiry_date, last_entry, last_exit])

    key = f"daily/overstayers_{today.isoformat()}.csv"
    tmp = f"/tmp/{os.path.basename(key)}"
    with open(tmp, "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerow(["passport_no", "visa_id", "expiry_date", "last_entry", "last_exit"])
        writer.writerows(illegal)

    s3 = boto3.client('s3')
    s3.upload_file(tmp, bucket, key)
    return {"count": len(illegal), "s3_key": key}
