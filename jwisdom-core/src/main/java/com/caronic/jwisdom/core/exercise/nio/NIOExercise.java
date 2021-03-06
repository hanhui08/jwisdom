package com.caronic.jwisdom.core.exercise.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by caronic on 2016/4/25.
 */
public class NIOExercise {

    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile(NIOExercise.class.getClassLoader().getResource("myfile.txt").getPath(), "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) { // while not be the end of stream
            System.out.println(bytesRead + " bytes read.");
            System.out.println(buffer.position());
            buffer.flip(); // flip the buffer for reading data from buffer
            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get());
            }

            buffer.clear(); // after read all data from buffer, clear it for reading data from channel
            bytesRead = channel.read(buffer);
        }
        file.close();
        channel.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(NIOExercise.class.getClassLoader().getResource("myfile.txt").getPath()), 48);
        while (bufferedReader.read() != -1) {
            System.out.println(bufferedReader.readLine());
        }
    }

}
