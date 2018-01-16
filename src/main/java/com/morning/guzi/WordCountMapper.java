package com.morning.guzi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by guzi on 2017/12/17.
 */
public class WordCountMapper extends Mapper<Object,Text,Text,IntWritable> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //super.map(key, value, context);
        IntWritable mapValue=new IntWritable(1);
        Text mapKey=new Text();
        StringTokenizer itr=new StringTokenizer(value.toString());
        while(itr.hasMoreTokens()){
            mapKey.set(itr.nextToken());
            context.write(mapKey,mapValue);
        }
    }


}
