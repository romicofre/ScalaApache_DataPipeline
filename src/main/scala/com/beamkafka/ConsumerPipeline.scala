package com.beamkafka
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.kafka.KafkaIO
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.kafka.common.serialization.Serdes

object ConsumerPipeline extends App{
  val applicationConf: Config = ConfigFactory.load("consumer.conf")
  val server = applicationConf.getString("consumer.bootstrap_servers")
  val groupId = applicationConf.getString("consumer.group_id")
  val topic = applicationConf.getString("consumer.topic")

  val options = PipelineOptionsFactory.create
  val pipeline = Pipeline.create(options)


//  val x: PCollection[KV[String, String]] = pipeline.apply(KafkaIO.read[String,String]()
//    .withBootstrapServers(server)
//    .withTopic(topic)
//    .withKeyDeserializer(classOf[StringDeserializer])
//    .withValueDeserializer(classOf[StringDeserializer])
//    .withoutMetadata()
//  )
  //.apply(MapElements.into(TypeDescriptors.strings()))


  /***
   * Working Fine
   */
//  val x: PCollection[KV[String, String]] = pipeline
//    .apply(KafkaIO.read[String, String]()
//      .withBootstrapServers(server)
//      .withTopics(util.Collections.singletonList(topic))
//      .withKeyDeserializer(classOf[StringDeserializer])
//      .withValueDeserializer(classOf[StringDeserializer])
//      .withoutMetadata()
//    )
//
//  val keysToInt: SerializableFunction[KV[String, String], KV[String, String]] = {
//    (input: KV[String, String]) => KV.of(input.getKey, input.getValue)
//  }
//
//  val y: PCollection[KV[String, String]] = x
//    .apply(MapElements
//      .into(TypeDescriptors.kvs(TypeDescriptors.strings, TypeDescriptors.strings))
//      .via(keysToInt))
//
//  println("LOOKING")
  /***
   * END working fine
   */

  val records = KafkaIO
    .read[java.lang.Long, String]()
    .withBootstrapServers(server)
    .withTopic(topic)
    .withKeyDeserializer(Serdes.Long.deserializer.getClass)
    .withValueDeserializer(Serdes.String.deserializer.getClass)

  import org.apache.beam.sdk.io.kafka.KafkaIO.Read
  assert(records.isInstanceOf[Read[_, _]])

  import org.apache.beam.sdk.Pipeline
  val p = Pipeline.create()

  p.apply("Records from first_topic", records)

  p.run().waitUntilFinish()

}

