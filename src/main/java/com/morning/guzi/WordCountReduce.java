package com.morning.guzi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * Created by guzi on 2017/12/17.
 */
public class WordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
    IntWritable result = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //super.reduce(key, values, context);
        //IntWritable sum=new IntWritable(0);
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        result.set(sum);
        context.write(key, result);


        /*private IntWritable result = new IntWritable();
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }*/
    }
}
