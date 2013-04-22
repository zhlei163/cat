package com.dianping.cat.consumer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.dianping.cat.consumer.advanced.CrossInfoTest;
import com.dianping.cat.consumer.advanced.DatabaseAnalyzerTest;
import com.dianping.cat.consumer.advanced.IpReportTest;
import com.dianping.cat.consumer.advanced.MatrixModelTest;
import com.dianping.cat.consumer.advanced.MetricAnalyzerTest;
import com.dianping.cat.consumer.advanced.SqlParsersTest;

@RunWith(Suite.class)
@SuiteClasses({

CrossInfoTest.class,

DatabaseAnalyzerTest.class,

IpReportTest.class,

MatrixModelTest.class,

SqlParsersTest.class,

DatabaseAnalyzerTest.class,

MetricAnalyzerTest.class

})
public class AllTests {

}
