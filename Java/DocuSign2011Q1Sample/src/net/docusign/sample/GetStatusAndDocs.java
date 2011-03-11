package net.docusign.sample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.docusign.api_3_0.APIServiceSoap;
import net.docusign.api_3_0.ArrayOfString2;
import net.docusign.api_3_0.EnvelopeStatusFilter;

/**
 * Servlet implementation class GetStatusAndDocs
 */
public class GetStatusAndDocs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatusAndDocs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void createStatusTable(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(Utils.SESSION_ENVELOPEIDS) != null) {
			APIServiceSoap api = Utils.getAPI(request);
			ArrayOfString2 envIDs =  (String[]) ((List<String>) session.getAttribute(Utils.SESSION_ENVELOPEIDS)).toArray();
			EnvelopeStatusFilter filter = new EnvelopeStatusFilter();
			filter.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
			filter.setEnvelopeIds(envIDs);
		}
	}
}
