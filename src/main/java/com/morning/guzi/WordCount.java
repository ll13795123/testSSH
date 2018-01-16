package com.morning.guzi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by guzi on 2017/12/17.
 */
public class WordCount {
    private static Logger logger = LoggerFactory.getLogger(WordCount.class);

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        if (args.length != 2) {
            logger.info("文件输入输出路径参数不完整");
            System.out.println("hellv");
            System.exit(2);
        }
        //构建job
        Job job = null;
        try {
            Configuration conf = new Configuration();
            String[] otherArgs = new String[0];
            try {
                otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
            } catch (IOException e) {
                e.printStackTrace();
            }
            job = Job.getInstance(conf, "WordCount");
            job.setJarByClass(WordCount.class);
            job.setMapperClass(WordCountMapper.class);
            job.setCombinerClass(WordCountReduce.class);
            job.setReducerClass(WordCountReduce.class);
            job.setInputFormatClass(TextInputFormat.class);
            job.setNumReduceTasks(1);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
