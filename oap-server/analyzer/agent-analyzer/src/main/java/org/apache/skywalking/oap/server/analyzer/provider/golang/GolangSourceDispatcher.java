package org.apache.skywalking.oap.server.analyzer.provider.golang;

import org.apache.skywalking.apm.network.language.agent.v3.GolangMetric;
import org.apache.skywalking.oap.server.core.CoreModule;
import org.apache.skywalking.oap.server.core.analysis.IDManager;
import org.apache.skywalking.oap.server.core.analysis.TimeBucket;
import org.apache.skywalking.oap.server.core.source.*;
import org.apache.skywalking.oap.server.library.module.ModuleManager;

public class GolangSourceDispatcher {
    private final SourceReceiver sourceReceiver;

    public GolangSourceDispatcher(ModuleManager moduleManager) {
        this.sourceReceiver = moduleManager.find(CoreModule.NAME).provider().getService(SourceReceiver.class);
    }

    public void sendMetric(String service, String serviceInstance, GolangMetric golangMetric) {
        long minuteTimeBucket = TimeBucket.getMinuteTimeBucket(golangMetric.getTime());

        final String serviceId = IDManager.ServiceID.buildId(service, true);
        final String serviceInstanceId = IDManager.ServiceInstanceID.buildId(serviceId, serviceInstance);

        this.sendToStackProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getStackInUse());
        this.sendToHeapProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getHeapAlloc());
        this.sendToGCNumProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getGcNum());
        this.sendToGCPauseTimeProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getGcPauseTime());
        this.sendToGoroutineNumProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getGoroutineNum());
        this.sendToThreadNumProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getThreadNum());
        this.sendToCPUUsedRateProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getCpuUsedRate());
        this.sendToMemUsedRateProcess(service, serviceId, serviceInstance, serviceInstanceId, minuteTimeBucket, golangMetric.getMemUsedRate());
    }

    private void sendToStackProcess(String service,
                                   String serviceId,
                                   String serviceInstance,
                                   String serviceInstanceId,
                                   long timeBucket,
                                   long stackUsed) {
        ServiceInstanceGolangStack serviceInstanceGolangStack = new ServiceInstanceGolangStack();
        serviceInstanceGolangStack.setId(serviceInstanceId);
        serviceInstanceGolangStack.setName(serviceInstance);
        serviceInstanceGolangStack.setServiceId(serviceId);
        serviceInstanceGolangStack.setServiceName(service);
        serviceInstanceGolangStack.setTimeBucket(timeBucket);
        serviceInstanceGolangStack.setUsed(stackUsed);
        this.sourceReceiver.receive(serviceInstanceGolangStack);
    }

    private void sendToHeapProcess(String service,
                                    String serviceId,
                                    String serviceInstance,
                                    String serviceInstanceId,
                                    long timeBucket,
                                    long heapUsed) {
        ServiceInstanceGolangHeap serviceInstanceGolangHeap = new ServiceInstanceGolangHeap();
        serviceInstanceGolangHeap.setId(serviceInstanceId);
        serviceInstanceGolangHeap.setName(serviceInstance);
        serviceInstanceGolangHeap.setServiceId(serviceId);
        serviceInstanceGolangHeap.setServiceName(service);
        serviceInstanceGolangHeap.setTimeBucket(timeBucket);
        serviceInstanceGolangHeap.setUsed(heapUsed);
        this.sourceReceiver.receive(serviceInstanceGolangHeap);
    }

    private void sendToGCNumProcess(String service,
                                   String serviceId,
                                   String serviceInstance,
                                   String serviceInstanceId,
                                   long timeBucket,
                                   long num) {
        ServiceInstanceGolangGCNum serviceInstanceGolangGcNum = new ServiceInstanceGolangGCNum();
        serviceInstanceGolangGcNum.setId(serviceInstanceId);
        serviceInstanceGolangGcNum.setName(serviceInstance);
        serviceInstanceGolangGcNum.setServiceId(serviceId);
        serviceInstanceGolangGcNum.setServiceName(service);
        serviceInstanceGolangGcNum.setTimeBucket(timeBucket);
        serviceInstanceGolangGcNum.setNum(num);
        this.sourceReceiver.receive(serviceInstanceGolangGcNum);
    }

    private void sendToGCPauseTimeProcess(String service,
                                    String serviceId,
                                    String serviceInstance,
                                    String serviceInstanceId,
                                    long timeBucket,
                                    long time) {
        ServiceInstanceGolangGCPauseTime serviceInstanceGolangGcPauseTime = new ServiceInstanceGolangGCPauseTime();
        serviceInstanceGolangGcPauseTime.setId(serviceInstanceId);
        serviceInstanceGolangGcPauseTime.setName(serviceInstance);
        serviceInstanceGolangGcPauseTime.setServiceId(serviceId);
        serviceInstanceGolangGcPauseTime.setServiceName(service);
        serviceInstanceGolangGcPauseTime.setTimeBucket(timeBucket);
        serviceInstanceGolangGcPauseTime.setTime(time);
        this.sourceReceiver.receive(serviceInstanceGolangGcPauseTime);
    }

    private void sendToGoroutineNumProcess(String service,
                                          String serviceId,
                                          String serviceInstance,
                                          String serviceInstanceId,
                                          long timeBucket,
                                          long num) {
        ServiceInstanceGolangGoroutineNum serviceInstanceGolangGoroutineNum = new ServiceInstanceGolangGoroutineNum();
        serviceInstanceGolangGoroutineNum.setId(serviceInstanceId);
        serviceInstanceGolangGoroutineNum.setName(serviceInstance);
        serviceInstanceGolangGoroutineNum.setServiceId(serviceId);
        serviceInstanceGolangGoroutineNum.setServiceName(service);
        serviceInstanceGolangGoroutineNum.setTimeBucket(timeBucket);
        serviceInstanceGolangGoroutineNum.setNum(num);
        this.sourceReceiver.receive(serviceInstanceGolangGoroutineNum);
    }

    private void sendToThreadNumProcess(String service,
                                           String serviceId,
                                           String serviceInstance,
                                           String serviceInstanceId,
                                           long timeBucket,
                                           long num) {
        ServiceInstanceGolangThreadNum serviceInstanceGolangThreadNum = new ServiceInstanceGolangThreadNum();
        serviceInstanceGolangThreadNum.setId(serviceInstanceId);
        serviceInstanceGolangThreadNum.setName(serviceInstance);
        serviceInstanceGolangThreadNum.setServiceId(serviceId);
        serviceInstanceGolangThreadNum.setServiceName(service);
        serviceInstanceGolangThreadNum.setTimeBucket(timeBucket);
        serviceInstanceGolangThreadNum.setNum(num);
        this.sourceReceiver.receive(serviceInstanceGolangThreadNum);
    }

    private void sendToCPUUsedRateProcess(String service,
                                        String serviceId,
                                        String serviceInstance,
                                        String serviceInstanceId,
                                        long timeBucket,
                                        float usedRate) {
        ServiceInstanceGolangCPUUsedRate serviceInstanceGolangCPUUsedRate = new ServiceInstanceGolangCPUUsedRate();
        serviceInstanceGolangCPUUsedRate.setId(serviceInstanceId);
        serviceInstanceGolangCPUUsedRate.setName(serviceInstance);
        serviceInstanceGolangCPUUsedRate.setServiceId(serviceId);
        serviceInstanceGolangCPUUsedRate.setServiceName(service);
        serviceInstanceGolangCPUUsedRate.setTimeBucket(timeBucket);
        serviceInstanceGolangCPUUsedRate.setUsedRate(usedRate);
        this.sourceReceiver.receive(serviceInstanceGolangCPUUsedRate);
    }

    private void sendToMemUsedRateProcess(String service,
                                          String serviceId,
                                          String serviceInstance,
                                          String serviceInstanceId,
                                          long timeBucket,
                                          float usedRate) {
        ServiceInstanceGolangMemUsedRate serviceInstanceGolangMemUsedRate = new ServiceInstanceGolangMemUsedRate();
        serviceInstanceGolangMemUsedRate.setId(serviceInstanceId);
        serviceInstanceGolangMemUsedRate.setName(serviceInstance);
        serviceInstanceGolangMemUsedRate.setServiceId(serviceId);
        serviceInstanceGolangMemUsedRate.setServiceName(service);
        serviceInstanceGolangMemUsedRate.setTimeBucket(timeBucket);
        serviceInstanceGolangMemUsedRate.setUsedRate(usedRate);
        this.sourceReceiver.receive(serviceInstanceGolangMemUsedRate);
    }

}
