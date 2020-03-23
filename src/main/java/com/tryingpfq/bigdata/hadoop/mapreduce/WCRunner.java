package com.tryingpfq.bigdata.hadoop.mapreduce;

import jdk.nashorn.internal.scripts.JO;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author tryingpfq
 * @date 2020/3/23
 **/
public class WCRunner {

    public static void main(String[] args) throws IOException {

        //首先要描述一个作业
        Job job = Job.getInstance(new Configuration());

        //设置mapper类型
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce类型
        job.setReducerClass(WCReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //指定原始数据存放在哪里
        //参数1：里面是对哪个参数进行指定
        //参数2：文件在哪个路径下,这个路径下的所有文件都会去读的
        FileInputFormat.setInputPaths(job, new Path("input"));

        //指定处理结果的数据存放路径
        FileOutputFormat.setOutputPath(job, new Path("out"));

        //提交
        int isok = 0;
        try {
            isok = job.waitForCompletion(true)?0:-1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(isok);
    }
}
