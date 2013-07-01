package com.team4.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.TokenIterator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.team4.utils.exceptions.T4Code;
import com.team4.utils.exceptions.T4Exception;
import com.team4.utils.parser.IParser;
import com.team4.utils.type.IBaseType;
import com.team4.utils.util.FuncUtil;
import com.team4.utils.util.T4Log;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.http.HttpUtility.java
 * @Class Name    : HttpUtility
 * @Description : Http请求相关的类，可适用于大部分CS程序。
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:47:12
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
 */
public class HttpUtility {

    private static final String USER_AGENT = "User-Agent";
    private static final int DEFAULT_TIMEOUT = 15;
    private static final int KEEP_ALIVE_DURATION_SECS = 15;
    private static final int KEEP_ALIVE_MONITOR_INTERVAL_SECS = 5;

    private HttpUtility() {
HttpUtilityafdasf
HttpUtility
HttpUtilityadasda
adasdqewqasdada
    }

    public static IBaseType executeHttpRequest(Context context, HttpRequestBase request,
                                               IParser parser) throws T4Exception {
        HttpClient client = createHttpClient(DEFAULT_TIMEOUT);
        return executeHttpRequest(context, client, request, parser);
HttpUtilityHttpUtility
    }

    public static IBaseType executeHttpRequest(Context context, HttpClient client,
                                               HttpRequestBase request, IParser parser)
            throws T4Exception {
        HttpResponse response = execute(context, client, request);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            try {
                String content = EntityUtils.toString(response.getEntity());
                return parser.parse(content);
            } catch (ParseException e) {
                T4Log.v("ParseException:" + e.getMessage());
                throw new T4Exception(T4Code.PARSE_ERROR,
                        e.getLocalizedMessage());
            } catch (IOException e) {
                T4Log.v("IOException:" + e.getMessage());
                throw new T4Exception(T4Code.PARSE_ERROR,
                        e.getLocalizedMessage());
            }
        } else {
            throw new T4Exception(T4Code.NETWORK_ERROR, "Error code: "
                    + statusCode + ". Try again later.");
        }
    }

    public static HttpResponse execute(Context context, HttpClient client, HttpUriRequest request)
            throws T4Exception {
        if (!FuncUtil.networkIsConnected(context))
            throw new T4Exception(T4Code.NETWORK_ERROR, "Network not connected!");
        HttpResponse response = null;
        for (int retries = 0; response == null && retries < 5; retries++) {
            T4Log.v("execute before");
            try {
                response = client.execute(request);
            } catch (ClientProtocolException e) {
                T4Log.v("ClientProtocolException:" + e.getMessage());
                throw new T4Exception(T4Code.NETWORK_ERROR,
                        e.getLocalizedMessage());
            } catch (IOException e) {
                T4Log.v("IOException:" + e.getMessage());
                throw new T4Exception(T4Code.NETWORK_ERROR,
                        e.getLocalizedMessage());
            }
            T4Log.v("execute after");
        }

        if (response == null) {
            throw new T4Exception(T4Code.NETWORK_ERROR,
                    "Apache HTTPClient encountered an error. No response, try again.");
        }

        return response;
    }

    public static HttpGet createHttpGet(String url, String userAgent,
                                        List<BasicNameValuePair> nameValuePairs) {
        T4Log.v("creating HttpGet for: " + url);
        String query = URLEncodedUtils.format(nameValuePairs, HTTP.UTF_8);
        HttpGet httpGet = new HttpGet(url + "?" + query);
        httpGet.addHeader(USER_AGENT, userAgent);
        T4Log.v("Created: " + httpGet.getURI());
        return httpGet;
    }

    public static HttpPost createHttpPost(String url, String userAgent,
                                          List<BasicNameValuePair> nameValuePairs)
            throws UnsupportedEncodingException {
        T4Log.v("creating HttpPost for: " + url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(USER_AGENT, userAgent);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
        T4Log.v("Created: " + httpPost);
        return httpPost;
    }

	/*
     * public static HttpPost createHttpPostWithMultiPart(FileStreamBody body,
	 * String url, String userAgent, List<BasicNameValuePair> nameValuePairs)
	 * throws ASCException { ASCLog.v("createHttpPostWithMultiPart for: " +
	 * url); HttpPost httpPost = new HttpPost(url);
	 * httpPost.addHeader(USER_AGENT, userAgent); MultipartEntity entity = new
	 * MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE, null, null); try {
	 * for (BasicNameValuePair pair : nameValuePairs) {
	 * entity.addPart(pair.getName(), new StringBody(pair.getValue(),
	 * Charset.forName(HTTP.UTF_8))); } } catch (UnsupportedEncodingException e)
	 * { throw new ASCException(ErrorCode.Local.PARAMETER_INVALID,
	 * "Unable to encode http parameters."); } entity.addPart("file", body);
	 * httpPost.setEntity(entity); return httpPost; }
	 */

    public static final DefaultHttpClient createHttpClient(int timeOut) {
        HttpParams connParams = new BasicHttpParams();
        ConnManagerParams.setMaxConnectionsPerRoute(connParams,
                new ConnPerRoute() {
                    public int getMaxForRoute(HttpRoute route) {
                        return 10;
                    }
                });
        ConnManagerParams.setMaxTotalConnections(connParams, 20);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        SSLSocketFactory sf = null;
        KeyStore trustStore;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            sf = new EasySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
        }
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", sf, 443));

        T4ClientConnManager cm = new T4ClientConnManager(connParams,
                schemeRegistry);
        if (timeOut <= 0)
            timeOut = DEFAULT_TIMEOUT;
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(httpParams, false);
        HttpConnectionParams.setConnectionTimeout(httpParams, timeOut * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, timeOut * 1000);
        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
        return new DefaultHttpClient(cm, httpParams) {
            @Override
            protected ConnectionKeepAliveStrategy createConnectionKeepAliveStrategy() {
                return new T4KeepAliveStrategy();
            }

            @Override
            protected ConnectionReuseStrategy createConnectionReuseStrategy() {
                return new T4ConnectionReuseStrategy();
            }
        };
    }

    private static class T4KeepAliveStrategy implements
            ConnectionKeepAliveStrategy {
        public long getKeepAliveDuration(HttpResponse response,
                                         HttpContext context) {
            // Keep-alive for the shorter of 20 seconds or what the server
            // specifies.
            long timeout = KEEP_ALIVE_DURATION_SECS * 1000;

            HeaderElementIterator i = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (i.hasNext()) {
                HeaderElement element = i.nextElement();
                String name = element.getName();
                String value = element.getValue();
                if (value != null && name.equalsIgnoreCase("timeout")) {
                    try {
                        timeout = Math.min(timeout,
                                Long.parseLong(value) * 1000);
                    } catch (NumberFormatException e) {
                    }
                }
            }

            return timeout;
        }
    }

    private static class T4ConnectionReuseStrategy extends
            DefaultConnectionReuseStrategy {

        /**
         * Implements a patch out in 4.1.x and 4.2 that isn't available in 4.0.x
         * which fixes a bug where connections aren't reused when the response
         * is gzipped. See https://issues.apache.org/jira/browse/HTTPCORE-257
         * for info about the issue, and
         * http://svn.apache.org/viewvc?view=revision&revision=1124215 for the
         * patch which is copied here.
         */
        @Override
        public boolean keepAlive(final HttpResponse response,
                                 final HttpContext context) {
            if (response == null) {
                throw new IllegalArgumentException(
                        "HTTP response may not be null.");
            }
            if (context == null) {
                throw new IllegalArgumentException(
                        "HTTP context may not be null.");
            }

            // Check for a self-terminating entity. If the end of the entity
            // will
            // be indicated by closing the connection, there is no keep-alive.
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            Header teh = response.getFirstHeader(HTTP.TRANSFER_ENCODING);
            if (teh != null) {
                if (!HTTP.CHUNK_CODING.equalsIgnoreCase(teh.getValue())) {
                    return false;
                }
            } else {
                Header[] clhs = response.getHeaders(HTTP.CONTENT_LEN);
                // Do not reuse if not properly content-length delimited
                if (clhs == null || clhs.length != 1) {
                    return false;
                }
                Header clh = clhs[0];
                try {
                    int contentLen = Integer.parseInt(clh.getValue());
                    if (contentLen < 0) {
                        return false;
                    }
                } catch (NumberFormatException ex) {
                    return false;
                }
            }

            // Check for the "Connection" header. If that is absent, check for
            // the "Proxy-Connection" header. The latter is an unspecified and
            // broken but unfortunately common extension of HTTP.
            HeaderIterator hit = response.headerIterator(HTTP.CONN_DIRECTIVE);
            if (!hit.hasNext())
                hit = response.headerIterator("Proxy-Connection");

            // Experimental usage of the "Connection" header in HTTP/1.0 is
            // documented in RFC 2068, section 19.7.1. A token "keep-alive" is
            // used to indicate that the connection should be persistent.
            // Note that the final specification of HTTP/1.1 in RFC 2616 does
            // not
            // include this information. Neither is the "Connection" header
            // mentioned in RFC 1945, which informally describes HTTP/1.0.
            //
            // RFC 2616 specifies "close" as the only connection token with a
            // specific meaning: it disables persistent connections.
            //
            // The "Proxy-Connection" header is not formally specified anywhere,
            // but is commonly used to carry one token, "close" or "keep-alive".
            // The "Connection" header, on the other hand, is defined as a
            // sequence of tokens, where each token is a header name, and the
            // token "close" has the above-mentioned additional meaning.
            //
            // To get through this mess, we treat the "Proxy-Connection" header
            // in exactly the same way as the "Connection" header, but only if
            // the latter is missing. We scan the sequence of tokens for both
            // "close" and "keep-alive". As "close" is specified by RFC 2068,
            // it takes precedence and indicates a non-persistent connection.
            // If there is no "close" but a "keep-alive", we take the hint.

            if (hit.hasNext()) {
                try {
                    TokenIterator ti = createTokenIterator(hit);
                    boolean keepalive = false;
                    while (ti.hasNext()) {
                        final String token = ti.nextToken();
                        if (HTTP.CONN_CLOSE.equalsIgnoreCase(token)) {
                            return false;
                        } else if (HTTP.CONN_KEEP_ALIVE.equalsIgnoreCase(token)) {
                            // continue the loop, there may be a "close"
                            // afterwards
                            keepalive = true;
                        }
                    }
                    if (keepalive)
                        return true;
                    // neither "close" nor "keep-alive", use default policy

                } catch (ParseException px) {
                    // invalid connection header means no persistent connection
                    // we don't have logging in HttpCore, so the exception is
                    // lost
                    return false;
                }
            }

            // default since HTTP/1.1 is persistent, before it was
            // non-persistent
            return !ver.lessEquals(HttpVersion.HTTP_1_0);
        }
    }

    private static class T4ClientConnManager extends
            ThreadSafeClientConnManager {
        public T4ClientConnManager(HttpParams params, SchemeRegistry schreg) {
            super(params, schreg);
        }

        @Override
        public ClientConnectionRequest requestConnection(HttpRoute route,
                                                         Object state) {
            IdleConnectionCloserThread.ensureRunning(this,
                    KEEP_ALIVE_DURATION_SECS, KEEP_ALIVE_MONITOR_INTERVAL_SECS);
            return super.requestConnection(route, state);
        }
    }

    private static class IdleConnectionCloserThread extends Thread {
        private final T4ClientConnManager manager;
        private final int idleTimeoutSeconds;
        private final int checkIntervalMs;
        private static IdleConnectionCloserThread thread = null;

        public IdleConnectionCloserThread(T4ClientConnManager manager,
                                          int idleTimeoutSeconds, int checkIntervalSeconds) {
            super();
            this.manager = manager;
            this.idleTimeoutSeconds = idleTimeoutSeconds;
            this.checkIntervalMs = checkIntervalSeconds * 1000;
        }

        public static synchronized void ensureRunning(
                T4ClientConnManager manager, int idleTimeoutSeconds,
                int checkIntervalSeconds) {
            if (thread == null) {
                thread = new IdleConnectionCloserThread(manager,
                        idleTimeoutSeconds, checkIntervalSeconds);
                thread.start();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (this) {
                        wait(checkIntervalMs);
                    }
                    T4Log.v("Check Expired Connection.");
                    manager.closeExpiredConnections();
                    manager.closeIdleConnections(idleTimeoutSeconds,
                            TimeUnit.SECONDS);
                    synchronized (IdleConnectionCloserThread.class) {
                        if (manager.getConnectionsInPool() == 0) {
                            thread = null;
                            return;
                        }
                    }
                }
            } catch (InterruptedException e) {
                thread = null;
            }
        }
    }
}
