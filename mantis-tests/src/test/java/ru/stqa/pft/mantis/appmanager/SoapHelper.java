package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    //Получение списка проектов со страницы http://localhost/mantisbt-2.25.4/manage_proj_page.php
    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {


        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("mantisconnect")));
        return mc;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {

        MantisConnectPortType mc = getMantisConnect();

        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }


    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        String adminLogin = app.getProperty("web.adminLogin");
        String adminPassword = app.getProperty("web.adminPassword");

        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(adminLogin, adminPassword, BigInteger.valueOf(issue.getProject().getId()));

        IssueData issueData = new IssueData();

        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef((BigInteger.valueOf(issue.getProject().getId())), issue.getProject().getName()));
        issueData.setCategory(categories[0]);

        BigInteger issueId = mc.mc_issue_add(adminLogin, adminPassword, issueData);

        IssueData createdIssueData = mc.mc_issue_get(adminLogin, adminPassword, issueId);
        return new Issue().
                withId(createdIssueData.getId().intValue()).
                withSummary(createdIssueData.getSummary()).
                withDescription(createdIssueData.getDescription()).
                withProject(new Project().
                        withId(createdIssueData.getId().intValue()).
                        withName(createdIssueData.getProject().getName()))

                ;


    }
    public String getIssueStatus(int id) throws MalformedURLException, ServiceException, RemoteException {

        String adminLogin = app.getProperty("web.adminLogin");
        String adminPassword = app.getProperty("web.adminPassword");

        MantisConnectPortType mc = getMantisConnect();

        IssueData issueData = mc.mc_issue_get(adminLogin, adminPassword, BigInteger.valueOf(id));

        return issueData.getStatus().getName();
    }

}
