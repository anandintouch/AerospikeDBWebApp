/**
 * 
 */
package com.aerospike.db.validator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 
 * @author anandprakash
 *
 */
public class NullHostNameVerifier implements HostnameVerifier {

	/* (non-Javadoc)
	 * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String, javax.net.ssl.SSLSession)
	 */
	public boolean verify(String hostname, SSLSession session) {
		// TODO Auto-generated method stub
		if (hostname.indexOf("aerospike.com") != -1) {
            return true;
        }
		
		return false;
	}

}
