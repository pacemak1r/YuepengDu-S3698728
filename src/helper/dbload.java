package helper;

import java.io.*;

public class dbload {
    public static void main(String[] args) throws IOException {
        int pagesize=Integer.parseInt(args[1]);
        String datafile=args[2];
        System.out.println(pagesize);
        System.out.println(datafile);
        File file = new File(datafile);
        BufferedReader reader =  new BufferedReader(new FileReader(file));
        String tempString = null;

        String outputFile="heap";
        String outputFiletail="."+args[1];
        int fileCount=1;
        int sizeCount=0;

        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile+fileCount+outputFiletail));
        long startTime =  System.currentTimeMillis();
        for(int i=0;i<4;i++)
            reader.readLine();//remove headers
        int i=1;
        while ((tempString = reader.readLine()) != null) {
            String[] t=tempString.split(",");
            String personName=t[1];
            String birthDate=t[23];
            String birthPlace_label=t[25];
            String deathDate=t[40];
            String field_label=t[50];
            String genre_Label=t[52];
            String instrument_label=t[62];
            String nationality_label=t[73];
            String thumbnail=t[124];
            String description=t[137];
            String outString=personName+","+birthDate+","+birthPlace_label+","+deathDate+","+field_label+","+genre_Label+","+instrument_label+","+nationality_label+","+thumbnail+","+description+"\n";
            Integer wikiPageID;
            try{
                wikiPageID=Integer.parseInt(t[133]);
            }catch(NumberFormatException e)
            {
                wikiPageID=0;
            }

            Integer StringLen=outString.getBytes().length+6;
            sizeCount+=StringLen;
            if(sizeCount>=pagesize){
                out.flush();
                out.close();
                out = new BufferedWriter(new FileWriter(outputFile+fileCount+outputFiletail));
                sizeCount=StringLen;

                long endTime =  System.currentTimeMillis();
                System.out.println("records:"+String.valueOf(i)+" page:"+String.valueOf(fileCount)+" time:"+(endTime-startTime)+"ms");

                fileCount++;
            }

            out.write(outString);
            out.write(wikiPageID);
            out.newLine();
            i++;
        }
        out.flush();
        out.close();
    }
}
