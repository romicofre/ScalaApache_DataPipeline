package com.kafka

import com.typesafe.scalalogging.Logger


object consumer extends App{
  lazy val logger = Logger("kafka")
  logger info "Starting to consume messages"

  //  logger debug "Starting to consume messages"
}
