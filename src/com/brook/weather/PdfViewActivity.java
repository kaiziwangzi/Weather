package com.brook.weather;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.brook.weather.constants.Constants;
import com.brook.weather.utils.FileUtil;
import com.joanzapata.pdfview.PDFView;

import android.os.AsyncTask;

public class PdfViewActivity extends BaseActivity{

	private String outfilepath;
    private File outfile;
    private String urlpath;
    private PDFView mPDFview;
    private String pdfName = "pdftemp.pdf";
	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_pdfview,
				R.string.pdfviewer, MODE_BACK);
	}

	@Override
	protected void setUpData() {
		urlpath = getIntent().getStringExtra("path");
		 DownloadPDF downloadpdf=new DownloadPDF();
         downloadpdf.execute();
	}

	@Override
	protected void setUpView() {
		mPDFview = (PDFView) findViewById(R.id.pdfView);
	}
	
	class DownloadPDF extends AsyncTask<String, String, String>{
	    @Override
	    protected String doInBackground(String... params) {
	        // TODO Auto-generated method stub
	        URL url;
	        try {
	            url = new URL(urlpath);
	            HttpURLConnection conn=(HttpURLConnection) url.openConnection();

	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Charset", "UTF-8");
	            conn.setDoInput(true);
	            conn.setConnectTimeout(3000);
	            conn.connect();
	            if(HttpURLConnection.HTTP_OK==conn.getResponseCode()){
	                byte[] bytes=new byte[1024];
	                InputStream is=conn.getInputStream();
	                 outfile=new File(FileUtil.getPath(PdfViewActivity.this)+Constants.CACHEPATH,pdfName);

	                if(!outfile.exists()){
	                    outfile.createNewFile();
	                }
	                FileOutputStream fos=new FileOutputStream(outfile);
	                int len=-1;
	                while((len=is.read(bytes))>0){
	                    fos.write(bytes,0,len);
	                }
	                    fos.flush();
	                    fos.close();
	            }

	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }finally{

	        }
	        return "下载完成";
	    }
	      @Override
	    protected void onPostExecute(String result) {
	          mPDFview.fromFile(outfile)
	            .defaultPage(1)
	            .showMinimap(false)
	            .enableSwipe(true)        
	            .load();

	        super.onPostExecute(result);
	    } 
	      @Override
	    protected void onProgressUpdate(String... values) {
	          super.onProgressUpdate(values);

	    }

	   }

}
