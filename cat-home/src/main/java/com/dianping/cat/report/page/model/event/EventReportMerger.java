package com.dianping.cat.report.page.model.event;

import com.dianping.cat.consumer.core.EventStatisticsComputer;
import com.dianping.cat.consumer.event.model.entity.EventName;
import com.dianping.cat.consumer.event.model.entity.EventReport;
import com.dianping.cat.consumer.event.model.entity.EventType;
import com.dianping.cat.consumer.event.model.entity.Machine;
import com.dianping.cat.consumer.event.model.entity.Range;
import com.dianping.cat.consumer.event.model.transform.DefaultMerger;
import com.dianping.cat.helper.CatString;

public class EventReportMerger extends DefaultMerger {
	public EventReportMerger(EventReport eventReport) {
		super(eventReport);

		eventReport.accept(new EventStatisticsComputer());
	}

	@Override
	public void mergeMachine(Machine old, Machine machine) {
	}

	@Override
	public void mergeName(EventName old, EventName other) {
		old.setTotalCount(old.getTotalCount() + other.getTotalCount());
		old.setFailCount(old.getFailCount() + other.getFailCount());

		if (old.getTotalCount() > 0) {
			old.setFailPercent(old.getFailCount() * 100.0 / old.getTotalCount());
		}

		if (old.getSuccessMessageUrl() == null) {
			old.setSuccessMessageUrl(other.getSuccessMessageUrl());
		}

		if (old.getFailMessageUrl() == null) {
			old.setFailMessageUrl(other.getFailMessageUrl());
		}
	}

	@Override
	public void mergeRange(Range old, Range range) {
		old.setCount(old.getCount() + range.getCount());
		old.setFails(old.getFails() + range.getFails());
	}

	public Machine mergesForAllMachine(EventReport report) {
		Machine machine = new Machine(CatString.ALL_IP);

		for (Machine m : report.getMachines().values()) {
			if (!m.getIp().equals(CatString.ALL_IP)) {
				visitMachineChildren(machine, m);
			}
		}

		return machine;
	}

	@Override
	public void mergeType(EventType old, EventType other) {
		old.setTotalCount(old.getTotalCount() + other.getTotalCount());
		old.setFailCount(old.getFailCount() + other.getFailCount());

		if (old.getTotalCount() > 0) {
			old.setFailPercent(old.getFailCount() * 100.0 / old.getTotalCount());
		}

		if (old.getSuccessMessageUrl() == null) {
			old.setSuccessMessageUrl(other.getSuccessMessageUrl());
		}

		if (old.getFailMessageUrl() == null) {
			old.setFailMessageUrl(other.getFailMessageUrl());
		}
	}


	@Override
	public void visitEventReport(EventReport eventReport) {
		super.visitEventReport(eventReport);

		EventReport report = getEventReport();
		report.getDomainNames().addAll(eventReport.getDomainNames());
		report.getIps().addAll(eventReport.getIps());
	}

}
