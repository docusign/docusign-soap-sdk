/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package net.docusign.receiver;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class Receiver
 */
public class Receiver extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Receiver() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO: Check for presence of xml files and build a nice table like
        // PHP sample

        // Start building page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");

        String realpathname = this.getServletContext().getRealPath("/");
        File xmlfiles = new File(realpathname);
        FilenameFilter xmlfilter = new OnlyXml();
        String[] xmlfilelist = xmlfiles.list(xmlfilter);
        // Do we have any files?
        if ((xmlfilelist != null) && (xmlfilelist.length > 0)) {
            // If so, list them as links
            out.println("<p>There are " + Integer.toString(xmlfilelist.length)
                    + " xml files</p>");
            out.println("<ul>");

            // iterate through the files and list as links
            String urlfilepath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + "/" + request.getContextPath() + "/";
            for (int findex = 0; findex < xmlfilelist.length; findex++) {
                out.println("<li><a href=\"" + urlfilepath
                        + xmlfilelist[findex] + "\">" + xmlfilelist[findex]
                        + "</a></li>");
            }
            out.println("</ul>");
        }
        else {
            // else give a default message
            out.println("<h1>No XML Files to list</h1>");
        }
        // finish page
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        InputStream instream = request.getInputStream();
        BufferedReader inread = new BufferedReader(new InputStreamReader(
                instream, "UTF-8"));
        while ((line = inread.readLine()) != null) {
            sb.append(line);
        }
        // TODO Strip out PDF and save as separate file
        String inxml = sb.toString();
        String nowTime = Long.toString(System.currentTimeMillis());
        String fileName = this.getServletContext().getRealPath("/") + "/"
                + nowTime + ".xml";
        FileOutputStream fos = new FileOutputStream(fileName);
        Writer out = new OutputStreamWriter(fos, "UTF-8");
        out.write(inxml);
        out.close();
    }
}
