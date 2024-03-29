/**
 *
 */
package com.zx.sms.codec.sgip12.msg;

import com.zx.sms.codec.cmpp.msg.Header;
import com.zx.sms.codec.sgip12.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipActiveTestResponseMessage extends SgipDefaultMessage {
	private static final long serialVersionUID = 4638514500085975L;
	public SgipActiveTestResponseMessage() {
		super(SgipPacketType.ACTIVETESTRESPONSE);
	}
	public SgipActiveTestResponseMessage(Header header) {
		super(SgipPacketType.ACTIVETESTRESPONSE,header);
	}

}
