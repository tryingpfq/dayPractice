package com.tryingpfq.bigdata.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tryingpfq
 * @date 2020/3/23
 **/
public class WCReduce extends Reducer<Text, IntWritable, Text, Text> {
    /**
     * map处理之后，传过来的是一个value集合
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;

        // value 传过来的集合
        for (IntWritable value : values) {
            count += value.get();
        }
        context.write(key, new Text(count + ""));

    }
}
