package com.beam

import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.Validation.Required
import org.apache.beam.sdk.options.{Default, Description, PipelineOptions, PipelineOptionsFactory}
import org.apache.beam.sdk.transforms.DoFn.ProcessElement
import org.apache.beam.sdk.transforms._
import org.apache.beam.sdk.values.KV

import java.lang

object FilePipeline {

  def main(args: Array[String]): Unit = {

    val options = PipelineOptionsFactory
      .fromArgs(args: _*)
      .withValidation()
      .as(classOf[WordCountOptions])

    val pipeline = Pipeline.create(options)

    pipeline.apply("ReadFiles", TextIO.read().from(options.getInputFile))
      .apply(ParDo.of(new ExtractWords))
      .apply(Count.perElement()) //Ignore IntelliJ error: "Cannot resolve apply". The code will compile.
      .apply(MapElements.via(new FormatResult))
      .apply("WriteWords", TextIO.write().to(options.getOutput))

    println("Starting ... ")
    pipeline.run().waitUntilFinish()
  }
}

// options
trait WordCountOptions extends PipelineOptions {

  @Description("Path of the file to read from")
  @Default.String("data/input_test.txt")
  def getInputFile: String
  def setInputFile(path: String)

  @Description("Path of the file to write to")
  @Required
  @Default.String("data/output")
  def getOutput: String
  def setOutput(path: String)

}


// udf

class FormatResult extends SimpleFunction[KV[String, java.lang.Long], String] {
  override def apply(input: KV[String, lang.Long]): String = {
    println("FormatResult")
    println(input.getKey)
    println(input.getValue)
    input.getKey + ": " + input.getValue
  }
}

class ExtractWords extends DoFn[String, String] {
  @ProcessElement
  def processElement(c: ProcessContext): Unit = {
    println("processElement")
    println(c.element())
    for (word <- c.element().split("[^\\p{L}]+")) yield {
      if (!word.isEmpty) c.output(word)
    }
  }
}
