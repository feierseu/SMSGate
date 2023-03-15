package com.zx.sms.connect.manager.smgp;

import com.zx.sms.codec.smgp.msg.MsgId;
import com.zx.sms.codec.smgp.msg.SMGPDeliverMessage;
import com.zx.sms.codec.smgp.msg.SMGPDeliverRespMessage;
import com.zx.sms.codec.smgp.msg.SMGPReportData;
import com.zx.sms.codec.smgp.msg.SMGPSubmitMessage;
import com.zx.sms.codec.smgp.msg.SMGPSubmitRespMessage;
import com.zx.sms.handler.api.smsbiz.MessageReceiveHandler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

public class SMGPMessageReceiveHandler extends MessageReceiveHandler {

	@Override
	protected ChannelFuture reponse(final ChannelHandlerContext ctx, Object msg) {
		
		if(msg instanceof SMGPDeliverMessage){
			SMGPDeliverRespMessage resp = new SMGPDeliverRespMessage();
		    resp.setSequenceNo(((SMGPDeliverMessage)msg).getSequenceNo());
		    resp.setMsgId( ((SMGPDeliverMessage)msg).getMsgId());
		    resp.setStatus(0);
		  
			return ctx.writeAndFlush(resp);
		}else if(msg instanceof SMGPSubmitMessage) {
			SMGPSubmitRespMessage resp = new SMGPSubmitRespMessage();
			SMGPSubmitMessage sumitMsg = (SMGPSubmitMessage)msg;
			
			resp.setSequenceNo(sumitMsg.getSequenceNo());
		    resp.setStatus(0);
		    ChannelFuture f = ctx.writeAndFlush(resp);
		    if(((SMGPSubmitMessage)msg).isNeedReport()) {
		    	SMGPDeliverMessage deliver = new SMGPDeliverMessage();
		    	deliver.setDestTermId(sumitMsg.getSrcTermId());
		    	deliver.setSrcTermId(sumitMsg.getDestTermIdArray()[0]);
		    	SMGPReportData report = new SMGPReportData();
		    	report.setStat("DELIVED");
		    	report.setDlvrd("111");
				report.setDoneTime("20180703000111");
				report.setErr("000");
				report.setMsgId(new MsgId());
				report.setSub("asf");
				report.setSubTime("20180703000111");
				report.setTxt("tst");
		    	deliver.setReport(report);
		    	ctx.writeAndFlush(deliver);
		    }
			return f;
		}
		return null;
	}

}
