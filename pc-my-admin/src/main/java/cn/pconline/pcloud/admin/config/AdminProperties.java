package cn.pconline.pcloud.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cn.pconline.pcloud.base.config.BaseProperties;

@Configuration
public class AdminProperties extends BaseProperties {

    // UPC系统上的应用名称
    @Value("${upc.app-name:}")
    private String upcAppName;

    //UPC系统的主域名
    @Value("${upc.root:}")
    private String upcRoot;

    //排行榜封面图用到的UPC指令
    @Value("${upc.command.rank-cover:}")
    private String rankCoverCommands;
    
    //大类封面图用到的UPC指令
    @Value("${upc.command.bigtype-cover:}")
    private String bigTypeCoverCommands;

    @Override
    public String getUpcAppName() {
        return upcAppName;
    }

    @Override
    public void setUpcAppName(String upcAppName) {
        this.upcAppName = upcAppName;
    }

    @Override
    public String getUpcRoot() {
        return upcRoot;
    }

    @Override
    public void setUpcRoot(String upcRoot) {
        this.upcRoot = upcRoot;
    }

    public String getRankCoverCommands() {
        return rankCoverCommands;
    }

    public void setRankCoverCommands(String rankCoverCommands) {
        this.rankCoverCommands = rankCoverCommands;
    }

	public String getBigTypeCoverCommands() {
		return bigTypeCoverCommands;
	}

	public void setBigTypeCoverCommands(String bigTypeCoverCommands) {
		this.bigTypeCoverCommands = bigTypeCoverCommands;
	}
    
}
