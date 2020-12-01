package com.ruoyi.games.domain;

import java.util.List;

public class RebateInfo {

    private List<RebateScaleInfo> infos;

    private SystemFunctionStatusInfo info;

    public List<RebateScaleInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<RebateScaleInfo> infos) {
        this.infos = infos;
    }

    public SystemFunctionStatusInfo getInfo() {
        return info;
    }

    public void setInfo(SystemFunctionStatusInfo info) {
        this.info = info;
    }
}
