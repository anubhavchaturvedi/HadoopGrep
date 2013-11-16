import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class grepMapper extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable,Text> {

	private String pattern[];
	
	public void configure(JobConf job) {

		//debugging
		System.out.println("Inside configure function printing elements of list: ");
		
		Scanner scan;
		try {
			scan = new Scanner(new File(job.get("patternFile")));
			LinkedList<String> list=new LinkedList<String>();
			while(scan.hasNext())
			{	
				list.add(scan.nextLine());
				
				//debugging
				System.out.println(list.peekLast());
			}
			scan.close();
			pattern=new String[list.size()];
			list.toArray(pattern);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//debugging
		System.out.println("Inside configure function printing elements of pattern[]: ");
		for(int i=0;i<pattern.length;i++)
		{
			System.out.println(pattern[i]);
		}
		
	}
	
		public void map(LongWritable key, Text values,
				OutputCollector<LongWritable,Text> output, Reporter reporter) throws IOException 
		{
			//debugging
			System.out.println("Inside map() function : ");
			System.out.println("values =\t  "+values.toString()+"\n");
			
			for(int i=0;i<pattern.length;i++)
			{
				if(values.toString().contains(pattern[i]))
				{
					//debugging
					System.out.println("*********VALUE MATCHED**********");

					output.collect(key, values);
					break;
				}
			}
		}
	}

