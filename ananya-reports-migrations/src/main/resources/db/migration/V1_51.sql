UPDATE report.campaign_dimension
SET obd_message_duration =
  CASE
    WHEN campaign_id = 'WEEK1' THEN 108
    WHEN campaign_id = 'WEEK2' THEN 105
    WHEN campaign_id = 'WEEK3' THEN 107
    WHEN campaign_id = 'WEEK4' THEN 108
    WHEN campaign_id = 'WEEK5' THEN 107
    WHEN campaign_id = 'WEEK6' THEN 108
    WHEN campaign_id = 'WEEK7' THEN 103
    WHEN campaign_id = 'WEEK8' THEN 108
    WHEN campaign_id = 'WEEK9' THEN 103
    WHEN campaign_id = 'WEEK10' THEN 104
    WHEN campaign_id = 'WEEK11' THEN 103
    WHEN campaign_id = 'WEEK12' THEN 104
    WHEN campaign_id = 'WEEK13' THEN 102
    WHEN campaign_id = 'WEEK14' THEN 104
    WHEN campaign_id = 'WEEK17' THEN 105
    WHEN campaign_id = 'WEEK18' THEN 106
    WHEN campaign_id = 'WEEK19' THEN 107
    WHEN campaign_id = 'WEEK20' THEN 107
    WHEN campaign_id = 'WEEK21' THEN 104
    WHEN campaign_id = 'WEEK22' THEN 102
    WHEN campaign_id = 'WEEK23' THEN 104
    WHEN campaign_id = 'WEEK24' THEN 105
    WHEN campaign_id = 'WEEK37' THEN 105
    WHEN campaign_id = 'WEEK38' THEN 106
    WHEN campaign_id = 'WEEK39' THEN 107
    WHEN campaign_id = 'WEEK40' THEN 106
    WHEN campaign_id = 'WEEK41' THEN 106
    WHEN campaign_id = 'WEEK42' THEN 100
    WHEN campaign_id = 'WEEK43' THEN 108
    WHEN campaign_id = 'WEEK44' THEN 107
    ELSE 300
  END,
inbox_message_duration =
  CASE
    WHEN campaign_id = 'WEEK1' THEN 90
    WHEN campaign_id = 'WEEK2' THEN 87
    WHEN campaign_id = 'WEEK3' THEN 89
    WHEN campaign_id = 'WEEK4' THEN 90
    WHEN campaign_id = 'WEEK5' THEN 88
    WHEN campaign_id = 'WEEK6' THEN 90
    WHEN campaign_id = 'WEEK7' THEN 84
    WHEN campaign_id = 'WEEK8' THEN 90
    WHEN campaign_id = 'WEEK9' THEN 84
    WHEN campaign_id = 'WEEK10' THEN 86
    WHEN campaign_id = 'WEEK11' THEN 85
    WHEN campaign_id = 'WEEK12' THEN 86
    WHEN campaign_id = 'WEEK13' THEN 83
    WHEN campaign_id = 'WEEK14' THEN 85
    WHEN campaign_id = 'WEEK17' THEN 86
    WHEN campaign_id = 'WEEK18' THEN 88
    WHEN campaign_id = 'WEEK19' THEN 89
    WHEN campaign_id = 'WEEK20' THEN 89
    WHEN campaign_id = 'WEEK21' THEN 86
    WHEN campaign_id = 'WEEK22' THEN 84
    WHEN campaign_id = 'WEEK23' THEN 86
    WHEN campaign_id = 'WEEK24' THEN 87
    WHEN campaign_id = 'WEEK37' THEN 86
    WHEN campaign_id = 'WEEK38' THEN 87
    WHEN campaign_id = 'WEEK39' THEN 88
    WHEN campaign_id = 'WEEK40' THEN 87
    WHEN campaign_id = 'WEEK41' THEN 88
    WHEN campaign_id = 'WEEK42' THEN 81
    WHEN campaign_id = 'WEEK43' THEN 89
    WHEN campaign_id = 'WEEK44' THEN 89
    ELSE 240
  END;