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
import net.docusign.api_3_0.FilteredEnvelopeStatuses;

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
        HttpSession session = request.getSession();
        if (session.getAttribute(Utils.SESSION_LOGGEDIN) == null
                || session.getAttribute(Utils.SESSION_LOGGEDIN).equals(false)) {
            response.sendRedirect(Utils.CONTROLLER_LOGIN);
        } else {
            if (session.getAttribute(Utils.SESSION_ENVELOPEIDS) != null) {
                APIServiceSoap api = Utils.getAPI(request);
                ArrayOfString2 envIDs = new ArrayOfString2();
                envIDs.getEnvelopeId().addAll((List<String>) session.getAttribute(Utils.SESSION_ENVELOPEIDS));
                EnvelopeStatusFilter filter = new EnvelopeStatusFilter();
                filter.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
                filter.setEnvelopeIds(envIDs);
                try {
                    FilteredEnvelopeStatuses statuses = api.requestStatusesEx(filter);
                    session.setAttribute(Utils.SESSION_STATUSES, statuses.getEnvelopeStatuses().getEnvelopeStatus());
                } catch (Exception e) {
                }
            }
            session.setAttribute(Utils.SESSION_EMBEDTOKEN, "");
            response.sendRedirect(Utils.PAGE_GETSTATUS);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
