package com.thoughtworks.mapreduce.vehicle;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class VehicleCountReducer extends Reducer<Text, IntWritable, NullWritable, Text> {
    @Override
    protected void reduce(Text vehicleType, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        Integer numberOfVehicles = 0;
        for (IntWritable intValue : values) {
            numberOfVehicles += intValue.get();
        }
        String output = "This " + vehicleType.toString() + " has " + numberOfVehicles;
        context.write(NullWritable.get(), new Text(output));
    }
}
