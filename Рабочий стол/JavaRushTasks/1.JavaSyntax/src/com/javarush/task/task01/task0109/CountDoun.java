package com.javarush.task.task01.task0109;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ruslan on 23.02.17.
 */
public class CountDoun {
    public static void main(String[] args) throws Exception{
//        CountDownLatch latch = new CountDownLatch(5);

//        Calendar calendar = Calendar.getInstance();
//        System.out.println(new SimpleDateFormat("d.MM.YYYY").format(calendar.getTime()));
//        Socket socket = new Socket();
//            System.out.println(Inet6Address.getLocalHost());
//        System.out.println(calendar);
        Path first = Paths.get("/initrd.img");
        Path parent = first.getParent();
        Files.createDirectories(parent);
        System.out.println(parent);
        String name = "na";
        long size = 123452334;
        long compressedSize = 32423422;

//        System.out.println(String.format("%d", size));
//        System.out.println(String.format("%s %d Kb(%d Kb) сжатие: %d%%", name, size/1024, compressedSize/1024, 100 - ((compressedSize * 100) / size)));
        Path path = Paths.get("/home/ruslan/Рабочий стол/JavaRushTasks/");
        Path path2 = Paths.get("1.JavaSyntax/Quest.info");

        System.out.println(path.resolve(path2));
//        System.out.println(Files.isDirectory(path.getParent()));
//        System.out.println(first.getParent().getParent() == null);
//        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
//        for (Path path1: stream)
//            System.out.println(path1);
    }

}
