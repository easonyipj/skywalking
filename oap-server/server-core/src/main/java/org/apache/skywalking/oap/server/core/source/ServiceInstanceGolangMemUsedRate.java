package org.apache.skywalking.oap.server.core.source;

import lombok.Getter;
import lombok.Setter;

import static org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.SERVICE_INSTANCE_CATALOG_NAME;
import static org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.SERVICE_INSTANCE_GOLANG_MEM_USED_RATE;

@ScopeDeclaration(id = SERVICE_INSTANCE_GOLANG_MEM_USED_RATE, name = "ServiceInstanceGolangMemUsedRate", catalog = SERVICE_INSTANCE_CATALOG_NAME)
@ScopeDefaultColumn.VirtualColumnDefinition(fieldName = "entityId", columnName = "entity_id", isID = true, type = String.class)
public class ServiceInstanceGolangMemUsedRate  extends Source {
    @Override
    public int scope() {
        return DefaultScopeDefine.SERVICE_INSTANCE_GOLANG_MEM_USED_RATE;
    }

    @Override
    public String getEntityId() {
        return String.valueOf(id);
    }

    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @ScopeDefaultColumn.DefinedByField(columnName = "name", requireDynamicActive = true)
    private String name;
    @Getter
    @Setter
    @ScopeDefaultColumn.DefinedByField(columnName = "service_name", requireDynamicActive = true)
    private String serviceName;
    @Getter
    @Setter
    @ScopeDefaultColumn.DefinedByField(columnName = "service_id")
    private String serviceId;
    @Getter
    @Setter
    private float usedRate;
}