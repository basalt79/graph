package at.aigner.graph.structure;

public class Version {

    private String buildTime;
    private String buildVersion;
    private String commitId;
    private String repositoryUrl;

    public String getBuildTime() {
        return buildTime;
    }

    public Version setBuildTime(String buildTime) {
        this.buildTime = buildTime;
        return this;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public Version setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
        return this;
    }

    public String getCommitId() {
        return commitId;
    }

    public Version setCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public Version setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
        return this;
    }

}
