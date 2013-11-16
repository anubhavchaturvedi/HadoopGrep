import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class grepReducer extends MapReduceBase implements Reducer<LongWritable, Text, LongWritable,Text> {

		public void reduce(LongWritable key, Iterator<Text> values,
				OutputCollector<LongWritable,Text> output, Reporter reporter) throws IOException {
			// replace KeyType with the real type of your key
			output.collect(key, values.next());
				// process value
			}
		}
