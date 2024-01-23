package com.swalfie.models

import java.sql.Timestamp

/**
 * InputRow representing txn
 *
 * @param event_timestamp
 * @param word
 */
@SerialVersionUID(100L)    
case class InputRow(
    ts_origin: Timestamp, 
    key: String,
    ts_event: Timestamp
)
