package com.example.dailybill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity
        implements CalendarView.OnCalendarSelectListener {
    private CalendarView mCalendarView;
    private CalendarLayout mCalendarLayout;
    private TextView mTitleDate;
    private EditText mEditText;
    private MyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private MyPopupWindow popupWindow;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_calendar);
        mCalendarLayout = findViewById(R.id.calender_layout);
        mCalendarView = findViewById(R.id.calendar_view);
        mCalendarView.setOnCalendarSelectListener(this);
        mTitleDate = findViewById(R.id.text_title_date);
        mTitleDate.setText(mCalendarView.getCurYear() + "-" + mCalendarView.getCurMonth() + "-" + mCalendarView.getCurDay());
        mEditText = findViewById(R.id.edit_msg);
        popupWindow = new MyPopupWindow(CalendarActivity.this);
        initAdapter();

        Button enterButton = findViewById(R.id.btn_enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();
                myAdapter.addItem(new BillItem(str, str.length()),myAdapter.getItemCount());
                mEditText.setText("");
            }
        });

        Button expandButton = findViewById(R.id.btn_expand);
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCalendarLayout.isExpand()){
                    expandButton.setText(R.string.expand);
                    mCalendarLayout.setModeOnlyWeekView();
                    mCalendarLayout.shrink();
                }
                else{
                    expandButton.setText(R.string.shrink);
                    mCalendarLayout.setModeOnlyMonthView();
                    mCalendarLayout.expand();
                }
            }
        });
    }

    private void initAdapter() {
        List<BillItem> dataList = new ArrayList<>();
        dataList.add(new BillItem("总计", 0));

        myAdapter = new MyAdapter(dataList);
        myAdapter.setLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                if(position == 0){
                    return;
                }
                MyViewHolder vh = (MyViewHolder)mRecyclerView.findViewHolderForAdapterPosition(position);
                popupWindow.Show(vh.amount);
                Button removeItemButton = popupWindow.getContentView().findViewById(R.id.btn_remove_item);
                removeItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAdapter.removeItem(position);
                        popupWindow.dismiss();
                    }
                });
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view_msg);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTitleDate.setText(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
    }

}