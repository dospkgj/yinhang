package yinhang.adapter;

import java.util.List;

import com.guo.yinhang.R;

import yinhang.entity.BaseEntity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private List<BaseEntity> list;
	private Context context;

	public MyAdapter(List<BaseEntity> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHold viewHold;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_shiti, null);
			viewHold = new ViewHold();
			viewHold.content = (TextView) convertView
					.findViewById(R.id.item_content);
			viewHold.answer = (TextView) convertView
					.findViewById(R.id.item_answer);
			viewHold.optionA = (TextView) convertView
					.findViewById(R.id.item_option_a);
			viewHold.optionB = (TextView) convertView
					.findViewById(R.id.item_option_b);
			viewHold.optionC = (TextView) convertView
					.findViewById(R.id.item_option_c);
			viewHold.optionD = (TextView) convertView
					.findViewById(R.id.item_option_d);
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}

		viewHold.content.setText(list.get(position).content);
		viewHold.answer.setText(list.get(position).answer);
		viewHold.optionA.setText(list.get(position).optionA);
		viewHold.optionB.setText(list.get(position).optionB);
		viewHold.optionC.setText(list.get(position).optionC);
		viewHold.optionD.setText(list.get(position).optionD);
		return convertView;

	}

	class ViewHold {
		TextView content;
		TextView answer;
		TextView optionA;
		TextView optionB;
		TextView optionC;
		TextView optionD;
	}
}
