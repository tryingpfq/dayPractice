package com.tryingpfq.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tryingpfq
 * @date 2020/3/23
 **/
public class WCMapper extends Mapper<LongWritable, Text, Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //每一行的数据
        String line = value.toString();

        String[] words = line.split(",");

        //写入到context中去进行reduce
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }

    }


}
