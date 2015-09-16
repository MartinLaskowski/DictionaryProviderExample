/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.example.com.dictionaryproviderexample;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.provider.UserDictionary.Words;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * This is the central activity for the Provider Dictionary Example App. The purpose of this app is
 * to show an example of accessing the {@link Words} list via its' Content Provider.
 */
public class MainActivity extends ActionBarActivity {

    // for the SimpleCursorAdapter to match the UserDictionary Columns to the layout items
    private static final String[] COLUMNS_TO_BE_BOUND = new String[] {
            Words.WORD,
            Words.FREQUENCY
    };

    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[] {
            android.R.id.text1,
            android.R.id.text2
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView which will be populated with the Dictionary ContentProvider data
        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view); // find the listView by ID

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);


        // Set the Adapter to fill the standard two-line-list-item layout with data from the Cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, // context .. The context where the ListView associated with this SimpleListItemFactory is running
                android.R.layout.two_line_list_item, // layout .. resource identifier of a layout file that defines the views for this list item. The layout file should include at least those named views defined in "to"
                cursor, // c .. The database cursor. Can be null if the cursor is not available yet
                COLUMNS_TO_BE_BOUND, // from .. A list of column names representing the data to bind to the UI. Can be null if the cursor is not available yet.
                LAYOUT_ITEMS_TO_FILL, // to	.. The views that should display column in the "from" parameter. These should all be TextViews. The first N views in this list are given the values of the first N columns in the from parameter. Can be null if the cursor is not available yet
                0); // flags .. used to determine the behavior of the adapter, as per CursorAdapter(Context, Cursor, int)   

        // Attache the Adapter to the ListView
        dictListView.setAdapter(adapter);
    }
}