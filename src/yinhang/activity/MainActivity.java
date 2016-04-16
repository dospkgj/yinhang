package yinhang.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import yinhang.adapter.MyAdapter;
import yinhang.entity.BaseEntity;
import yinhang.sql.SqlHelper;
import yinhang.utils.CopyUtils;

import com.guo.yinhang.R;
import com.umeng.update.UmengUpdateAgent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity implements OnClickListener {

	private EditText etKey;
	private List<BaseEntity> list = new ArrayList<BaseEntity>();
	private ListView listView;
	private Button btnClear;
	private Button btnSearch;
	private MyAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		doThings();
	}

	private void doThings() {
		copyDB();
		initView();
		update();
	}

	private void update() {
		UmengUpdateAgent.update(this);
	}

	private void copyDB() {
		File f = this.getDatabasePath(SqlHelper.DB_ALL_NAME);
		if (f.exists()) {
			f.delete();
		}
		CopyUtils.copyAssetFileToDatabase(this, SqlHelper.DB_ALL_NAME,
				SqlHelper.DB_ALL_NAME);
	}

	private static final String[] strs = {"机构理财","客户经理", "营销", "小企业" };
	private Spinner spinner;
	private CheckBox ckAn;

	private void initView() {
		etKey = (EditText) findViewById(R.id.main_et_key);
		listView = (ListView) findViewById(R.id.main_list);
		btnClear = (Button) findViewById(R.id.main_btn_clear);
		btnSearch = (Button) findViewById(R.id.main_btn_search);
		spinner = (Spinner) findViewById(R.id.main_sp_type);
		btnClear.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
		myAdapter = new MyAdapter(list, this);
		listView.setAdapter(myAdapter);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, strs);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		ckAn = (CheckBox) findViewById(R.id.main_ck_answer);
		etKey.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					search();
					return true;
				}
				return false;
			}
		});
	}

	private void search() {
		String string = etKey.getText().toString();
		list.clear();
		if (TextUtils.isEmpty(string)) {
			List<BaseEntity> all = new SqlHelper(this).getAll(null,
					spinner.getSelectedItemPosition(), ckAn.isChecked());
			list.addAll(all);
		} else {
			String[] split = string.split(" ");
			List<BaseEntity> all = new SqlHelper(this).getAll(split,
					spinner.getSelectedItemPosition(), ckAn.isChecked());
			list.addAll(all);
		}
		myAdapter.notifyDataSetChanged();
		listView.setSelection(0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_btn_clear:
			etKey.setText(null);
			break;
		case R.id.main_btn_search:
			search();
			break;
		default:
			break;
		}
	}

}
