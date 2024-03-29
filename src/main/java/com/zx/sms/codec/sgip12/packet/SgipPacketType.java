/**
 * 
 */
package com.zx.sms.codec.sgip12.packet;

import com.zx.sms.codec.cmpp.packet.PacketStructure;
import com.zx.sms.codec.cmpp.packet.PacketType;
import com.zx.sms.codec.sgip12.codec.SgipBindRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipBindResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipDeliverRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipDeliverResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipReportRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipReportResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipSubmitRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipSubmitResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipTraceRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipTraceResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipUnbindRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipUnbindResponseMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipActiveTestRequestMessageCodec;
import com.zx.sms.codec.sgip12.codec.SgipActiveTestResponseMessageCodec;

import io.netty.handler.codec.MessageToMessageCodec;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum SgipPacketType implements PacketType {
	BINDREQUEST(0x00000001, SgipBindRequest.class,SgipBindRequestMessageCodec.class),
	BINDRESPONSE(0x80000001, SgipBindResponse.class,SgipBindResponseMessageCodec.class),
	UNBINDREQUEST(0x00000002, SgipUnbindRequest.class,SgipUnbindRequestMessageCodec.class),
	UNBINDRESPONSE(0x80000002, SgipUnbindResponse.class,SgipUnbindResponseMessageCodec.class),
	ACTIVETESTREQUEST(0x01000001, SgipActiveTestRequest.class,SgipActiveTestRequestMessageCodec.class),
	ACTIVETESTRESPONSE(0x81000001, SgipActiveTestResponse.class,SgipActiveTestResponseMessageCodec.class),
	SUBMITREQUEST(0x00000003, SgipSubmitRequest.class,SgipSubmitRequestMessageCodec.class),
	SUBMITRESPONSE(0x80000003, SgipSubmitResponse.class,SgipSubmitResponseMessageCodec.class),
	DELIVERREQUEST(0x00000004, SgipDeliverRequest.class,SgipDeliverRequestMessageCodec.class),
	DELIVERRESPONSE(0x80000004, SgipDeliverResponse.class,SgipDeliverResponseMessageCodec.class),
	REPORTREQUEST(0x00000005, SgipReportRequest.class,SgipReportRequestMessageCodec.class),
	REPORTRESPONSE(0x80000005, SgipReportResponse.class,SgipReportResponseMessageCodec.class),
	TRACEREQUEST(0x00001000, SgipTraceRequest.class,SgipTraceRequestMessageCodec.class),
	TRACERESPONSE(0x80001000, SgipTraceResponse.class,SgipTraceResponseMessageCodec.class);

	
    private int commandId;
    private Class<? extends PacketStructure> packetStructure;
    private Class<? extends MessageToMessageCodec> codec;
    private SgipPacketType(int commandId, Class<? extends PacketStructure> packetStructure,Class<? extends MessageToMessageCodec> codec) {
        this.commandId = commandId;
        this.packetStructure = packetStructure;
        this.codec = codec;
    }
    public int getCommandId() {
        return commandId;
    }
    public PacketStructure[] getPacketStructures() {
    	return packetStructure.getEnumConstants();
    }

    public long getAllCommandId() {
        long defaultId = 0x0;
        long allCommandId = 0x0;
        for(SgipPacketType packetType : SgipPacketType.values()) {
            allCommandId |= packetType.commandId;
        }
        return allCommandId ^ defaultId;
    }
	@Override
	public MessageToMessageCodec getCodec() {
		try {
			return codec.newInstance();
		} catch (InstantiationException e) {
			return null;
		}
		catch(  IllegalAccessException e){
			return null;
		}
	}
}
