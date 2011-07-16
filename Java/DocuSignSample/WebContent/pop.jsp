<%@ page  session="true" 
    import="net.docusign.sample.Utils"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>DocuSign SDK - Frame Pop</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript">
	    function framepop() {
	        if (top.frames.length != 0)
	            top.location = '<%= Utils.CONTROLLER_GETSTATUS %>' + window.location.search;
	    }
	</script>
</head>
<body onload="framepop();">
</body>
</html>