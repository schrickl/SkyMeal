package com.bill.android.skymeal.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.bill.android.skymeal.R;
import com.bill.android.skymeal.activities.MenuActivity;

public class LastOrderSummaryWidget extends AppWidgetProvider {

    private static final String LOG_TAG = LastOrderSummaryWidget.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.order_summary_widget);
        views.setTextViewText(R.id.appwidget_text, context.getString(R.string.app_name));

        // Retrieve the last order from SharedPreferences
        SharedPreferences sharedPreferencesForWidget = context.getSharedPreferences(context.getString(R.string.widget_prefs), Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        float orderTotal = sharedPreferencesForWidget.getFloat(context.getString(R.string.order_total), (float) 0.00);
        String orderDetails = sharedPreferencesForWidget.getString(context.getString(R.string.order_details), "");
        views.setTextViewText(R.id.tv_widget_order_total, "$" + String.valueOf(orderTotal));
        views.setTextViewText(R.id.tv_widget_order_details, orderDetails);

        // Launch the app when clicked
        Intent intent = new Intent(context, MenuActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.ll_widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName widget = new ComponentName(context.getPackageName(), LastOrderSummaryWidget.class.getName());
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(widget);
        onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
        super.onReceive(context, intent);
    }
}
