package com.lior.packagestatus.client;

import java.util.List;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.view.client.ListDataProvider;
import com.lior.packagestatus.shared.CustomFlowPanel;
import com.lior.packagestatus.shared.DynamicImageResource;

public class PackageStatusView implements EntryPoint
{
  /**
   * Create a remote service to talk to the server-side service.
   */
  private final PackageStatusServiceAsync packageStatusService = GWT
      .create(PackageStatusService.class);

  static FlowPanel contentPanel = new CustomFlowPanel.Vertical();

  static List<PackageStatusDO> packageStatusList;

  static Column<PackageStatusDO, ImageResource> stateColumn =
    new Column<PackageStatusDO, ImageResource>(new ImageResourceCell()) {
      @Override
      public ImageResource getValue(PackageStatusDO object) {
        if(object.getState().equals(object)) {        
        }
        else if(object.getState().equals("BAD")) {
          return new DynamicImageResource("images/bad.png");
        }
        else if(object.getState().equals("RUNNING")) {
          return new DynamicImageResource("images/running.jpg");
        }
        else if(object.getState().equals("PASS")) {
          return new DynamicImageResource("images/pass.jpg");
        }
        else if(object.getState().equals("FAIL")) {
          return new DynamicImageResource("images/fail.png");
        }
        else {
          return new DynamicImageResource("images/other.png");
        }
        return null;
      }
    };
  /*
   * TEXT BASED
   *
  static Column<PackageStatusDO, String> stateColumn =
      new Column<PackageStatusDO, String>(new TextCell()) {
        @Override
        public String getValue(PackageStatusDO object) {
          return object.getState();
        }
      };
      */

  static Column<PackageStatusDO, String> idColumn =
      new Column<PackageStatusDO, String>(new TextCell()) {
        @Override
        public String getValue(PackageStatusDO object) {
          return object.getId();
        }
      };

  static Column<PackageStatusDO, String> messageColumn =
      new Column<PackageStatusDO, String>(new TextCell()) {
        @Override
        public String getValue(PackageStatusDO object) {
          return object.getMessage();
        }
      };

  private static Widget vGap(int gap) {
    Label l = new Label(" ");
    l.setPixelSize(10, gap);
    return l;
  }

  private static FlexTable titlePanel = new FlexTable();

  private static CellTable<PackageStatusDO> packageTable =
      new CellTable<PackageStatusDO>();

  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get();
    if (Document.get() != null) {
      Document.get().setTitle("Package Status");
    }

    FlowPanel rootFlowPanel = new CustomFlowPanel.Vertical();
    rootPanel.add(rootFlowPanel, 0, 0);
    rootFlowPanel.setSize("100%", "100%");

    // NORTH
    Image image = new Image("images/AlgoCore.png");
    image.setSize("50px", "50px");

    Label lblNewLabel = new Label("Algo Core Team Reports");
    lblNewLabel.setStyleName("headingTitle");
    lblNewLabel.setWordWrap(false);
    titlePanel.setStyleName((String) null);
    titlePanel.setCellSpacing(20);

    titlePanel.setWidget(0, 0, image);
    titlePanel.setWidget(0, 1, lblNewLabel);

    // CENTER -- DOWNLOADED DATA
    ScrollPanel scrollPanel = new ScrollPanel();
    scrollPanel.add(contentPanel);
    rootFlowPanel.add(scrollPanel);
    contentPanel.setWidth("100%");

    // main stuff
    contentPanel.clear();
    contentPanel.add(titlePanel);
    contentPanel.add(vGap(20));

    packageTable = new CellTable<PackageStatusDO>();
    packageTable.addColumn(stateColumn, "State");
    packageTable.addColumn(idColumn, "Package Id");
    packageTable.addColumn(messageColumn, "Info");
    contentPanel.add(packageTable);

    AsyncCallback<List<PackageStatusDO>> packageStatusHandler =
        new AsyncCallback<List<PackageStatusDO>>() {

          @Override
          public void onFailure(Throwable caught) {}

          @Override
          public void onSuccess(List<PackageStatusDO> result) {
            packageStatusList = result;
            ListDataProvider<PackageStatusDO> dataProvider =
                new ListDataProvider<PackageStatusDO>();
            dataProvider.getList().clear();
            dataProvider.getList().addAll(packageStatusList);
            dataProvider.addDataDisplay(packageTable);
          }
        };

    packageStatusService.findAllPackageStatus(packageStatusHandler);
  }
}
