Draft:
    Don't worry, more work will be done

Requirements:
    1. Install apache-cfx-2.3.2 or greater. Install from here: http://cxf.apache.org/download.html
    2. Tomcat 6 or greater. Install from here: http://tomcat.apache.org/download-60.cgi
    3. Java JDK 1.5 or greater.
    4. ant 1.7.1 or greater.
    
To build:
    1. change line in build.properties file to point to apache cxf lib directory e.g.:
            cxf.dir=/opt/apache-cxf/lib
    2. Optionally set elements in config.properties files
    4. Execute 'ant' while in same directory as build.xml file
    5. Use tomcat manager to deploy WAR file that is placed in dist directory
