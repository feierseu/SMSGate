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
public class SgipActiveTestRequestMessage extends SgipDefaultMessage {

	private static final long serialVersionUID = 6344903835739798820L;
	public SgipActiveTestRequestMessage() {
		super(SgipPacketType.ACTIVETESTREQUEST);
	}

	public SgipActiveTestRequestMessage(Header header) {
		super(SgipPacketType.ACTIVETESTREQUEST,header);
	}

}
