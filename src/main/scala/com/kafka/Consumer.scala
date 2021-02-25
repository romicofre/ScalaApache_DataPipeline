package com.kafka

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.Properties
import scala.collection.JavaConverters.iterableAsScalaIterableConverter


//import org.apache.kafka.clients.Consumer.KafkaConsumer
//import org.apache.kafka.common.serialization.StringDeserializer

object Consumer extends App{

    lazy val logger = Logger(Consumer.getClass.getName)
    logger info "Starting to consume messages"

    val applicationConf: Config = ConfigFactory.load("consumer.conf")
    val server = applicationConf.getString("consumer.bootstrap_servers")
    val groupId = applicationConf.getString("consumer.group_id")
    val topic = applicationConf.getString("consumer.topic")

    // Create consumer configs
    val properties = new Properties()
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server)
    //properties.put("group.id", "Consumer-tutorial")
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")



    //    val kafkaConsumer = new KafkaConsumer[String, String](properties)
    //    kafkaConsumer.subscribe("firstTopic", "secondTopic")
    //
    //    while (true) {
    //      val results = kafkaConsumer.poll(2000).asScala
    //      for ((topic, data) <- results) {
    //        // Do stuff
    //      }
    //    }



}
