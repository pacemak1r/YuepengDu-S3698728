package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class birthDate {
    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF)<<8)
                | ((src[offset+2] & 0xFF)<<16)
                | ((src[offset+3] & 0xFF)<<24));
        return value;
    }

    public static void main(String[] args) throws IOException {

        int min=Integer.parseInt(args[0]);
        int max=Integer.parseInt(args[1]);
        String outputFile="heap";
        File file = new File(System.getProperty("user.dir"));
        File[] fileList = file.listFiles();
        int fileListid=0;
        long startTime =  System.currentTimeMillis();
        String tempString = null;
        int record=1;
        while(fileListid<fileList.length){
            if(!fileList[fileListid].toString().contains("heap")){
                fileListid++;
                continue;
            }
            BufferedReader reader =  new BufferedReader(new FileReader(fileList[fileListid]));
            fileListid++;
            while((tempString = reader.readLine()) != null){
                String[] t=tempString.split(",");

                String personName=t[0];
                String birthDate=t[1];
                String birthPlace_label=t[2];
                String deathDate=t[3];
                String field_label=t[4];
                String genre_Label=t[5];
                String instrument_label=t[6];
                String nationality_label=t[7];
                String thumbnail=t[8];
                String description=t[9];
                char[] cy=new char[4];
                byte[] by=new byte[4];
                reader.read(cy,0,4);
                for(int j=0;j<4;j++)
                    by[j]=(byte) cy[j];
                Integer wikiPageID=bytesToInt(by,0);

                String year="";
                String month="";
                String day="";
                int i=0;
                while (i<birthDate.length()&&(birthDate.charAt(i) < 48 || birthDate.charAt(i) > 57)) {
                    i++;
                }
                while (i<birthDate.length()&&birthDate.charAt(i) >= 48 && birthDate.charAt(i) <= 57) {
                    year += birthDate.charAt(i);
                    i++;
                }
                while (i<birthDate.length()&&(birthDate.charAt(i) < 48 || birthDate.charAt(i) > 57)) {
                    i++;
                }
                while (i<birthDate.length()&&birthDate.charAt(i) >= 48 && birthDate.charAt(i) <= 57) {
                    month += birthDate.charAt(i);
                    i++;
                }
                while (i<birthDate.length()&&(birthDate.charAt(i) < 48 || birthDate.charAt(i) > 57)) {
                    i++;
                }
                while (i<birthDate.length()&&birthDate.charAt(i) >= 48 && birthDate.charAt(i) <= 57) {
                    day += birthDate.charAt(i);
                    i++;
                }
                int birth;
                if(year.length()==4&&(month.length()==1||month.length()==2)&& (day.length()==2||day.length()==1))
                    birth=Integer.parseInt(year)*10000+Integer.parseInt(month)*100+Integer.parseInt(day);
                else
                    birth=0;
                if(min<=birth&&birth<=max){
                    long endTime =  System.currentTimeMillis();
                    System.out.println("records:"+String.valueOf(record)+" personName:"+String.valueOf(personName)+" birthday:"+year+"/"+month+"/"+day+" wikiID:"+String.valueOf(wikiPageID)+" time:"+(endTime-startTime)+"ms");
                }
                record++;
            }
        }

    }
}
