/**
 * Copyright 2012 OpenKit
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openkit.leaderboards;

import io.openkit.OKScore;

import java.util.List;

import io.openkit.facebook.widget.ProfilePictureView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OKScoresListAdapter extends ArrayAdapter<OKScore>
{
	public OKScoresListAdapter(Context context, int resource, List<OKScore> objects)
	{
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		
		if(row == null){
			int listItemID = getContext().getResources().getIdentifier("io_openkit_listitem_okscore", "layout", getContext().getPackageName());
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			row = inflater.inflate(listItemID,  parent, false);
		}
		
		OKScore currentScore = this.getItem(position);
		
		int label1Id, label2Id, rankLabelId, pictureViewId;
		
		label1Id = getContext().getResources().getIdentifier("text1", "id", getContext().getPackageName());
		label2Id = getContext().getResources().getIdentifier("text2", "id", getContext().getPackageName());
		rankLabelId = getContext().getResources().getIdentifier("io_openkit_scoreRankTextView", "id", getContext().getPackageName());
		pictureViewId = getContext().getResources().getIdentifier("io_openkit_scorePicView", "id", getContext().getPackageName());
		
		TextView label1 = (TextView)row.findViewById(label1Id);
		TextView label2 = (TextView)row.findViewById(label2Id);
		TextView rankLabel = (TextView)row.findViewById(rankLabelId);
		ProfilePictureView pictureView = (ProfilePictureView)row.findViewById(pictureViewId);
		/*
		TextView label1 = (TextView)row.findViewById(io.openkit.R.id.text1);
		TextView label2 = (TextView)row.findViewById(io.openkit.R.id.text2);
		TextView rankLabel = (TextView)row.findViewById(io.openkit.R.id.io_openkit_scoreRankTextView);
		ProfilePictureView pictureView = (ProfilePictureView)row.findViewById(io.openkit.R.id.io_openkit_scorePicView);
		*/
		label2.setText(Integer.toString(currentScore.getScoreValue()));
		rankLabel.setText(Integer.toString(currentScore.getRank()));
		pictureView.setCropped(true);
		
		if(currentScore.getOKUser() != null)
		{
			label1.setText(currentScore.getOKUser().getUserNick());
			pictureView.setProfileId(Long.toString(currentScore.getOKUser().getFBUserID()));
		}
		
		row.setTag(position);
		
		return row;
	}

}
