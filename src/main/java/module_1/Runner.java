package module_1;

import java.io.*;
import java.util.*;

public class Runner {
   public static void main(String[] args) throws IOException {
      ListUtils array10k = new ListUtils(10_000, new ArrayList<>());
      array10k.populate();
      String arrayFor10k = array10k.getResults();

      ListUtils linked10k = new ListUtils(10_000, new LinkedList<>());
      linked10k.populate();
      String linkedFor10k = linked10k.getResults();

      SetUtils hash10K = new SetUtils(10_000, new HashSet<>());
      hash10K.populate();
      String hashFor10k = hash10K.getResults();

      SetUtils tree10K = new SetUtils(10_000, new TreeSet<>());
      tree10K.populate();
      String treeFor10k = tree10K.getResults();

      String header10k = String.format("%-14s%-10s%-10s%-10s%-10s%-12s%-10s%-10s%n", "10K (in ns)", "add", "get",
              "remove", "contains", "populate", "it.add", "it.remove");

      String results10k = String.format("%-14s%-72s%-14s%-72s%-14s%-72s%-14s%-72s", "ArrayList", arrayFor10k, "LinkedList",
              linkedFor10k, "HashSet", hashFor10k, "TreeSet", treeFor10k);

      System.out.print(header10k);
      System.out.println(results10k);

      ListUtils array100k = new ListUtils(100_000, new ArrayList<>());
      array100k.populate();
      String arrayFor100k = array100k.getResults();

      ListUtils linked100k = new ListUtils(100_000, new LinkedList<>());
      linked100k.populate();
      String linkedFor100k = linked100k.getResults();

      SetUtils hash100K = new SetUtils(100_000, new HashSet<>());
      hash100K.populate();
      String hashFor100k = hash100K.getResults();

      SetUtils tree100K = new SetUtils(100_000, new TreeSet<>());
      tree100K.populate();
      String treeFor100k = tree100K.getResults();

      String header100k = String.format("%-14s%-10s%-10s%-10s%-10s%-12s%-10s%-10s%n", "100K (in ns)", "add", "get",
              "remove", "contains", "populate", "it.add", "it.remove");
      String results100k = String.format("%-14s%-72s%-14s%-72s%-14s%-72s%-14s%-72s", "ArrayList", arrayFor100k, "LinkedList",
              linkedFor100k, "HashSet", hashFor100k, "TreeSet", treeFor100k);

      System.out.print(header100k);
      System.out.println(results100k);

      ListUtils array1000k = new ListUtils(1_000_000, new ArrayList<>());
      array1000k.populate();
      String arrayFor1000k = array1000k.getResults();

      ListUtils linked1000k = new ListUtils(1_000_000, new LinkedList<>());
      linked1000k.populate();
      String linkedFor1000k = linked1000k.getResults();

      SetUtils hash1000K = new SetUtils(1_000_000, new HashSet<>());
      hash1000K.populate();
      String hashFor1000k = hash1000K.getResults();

      SetUtils tree1000K = new SetUtils(1_000_000, new TreeSet<>());
      tree1000K.populate();
      String treeFor1000k = tree1000K.getResults();

      String header1000k = String.format("%-14s%-10s%-10s%-10s%-10s%-12s%-10s%-10s%n", "1000K (in ns)", "add", "get",
              "remove", "contains", "populate", "it.add", "it.remove");

      String results1000k = String.format("%-14s%-72s%-14s%-72s%-14s%-72s%-14s%-72s", "ArrayList", arrayFor1000k, "LinkedList",
              linkedFor1000k, "HashSet", hashFor1000k, "TreeSet", treeFor1000k);

      System.out.print(header1000k);
      System.out.println(results1000k);

      File file = new File("collections_effectiveness.txt");

      if (!file.createNewFile()) {
         System.out.println("File has been already created");
      }

      FileWriter writer = new java.io.FileWriter(file.getAbsolutePath());
      BufferedWriter bw = new BufferedWriter(writer);

      bw.write("collections effectiveness:\n\n" + header10k + results10k + "\n"
              + header100k + results100k + "\n" + header1000k + results1000k);
      bw.close();
   }
}