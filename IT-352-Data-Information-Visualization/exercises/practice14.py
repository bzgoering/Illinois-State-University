import pandas as pd
import altair as alt

pd.set_option('display.max_columns', None)
pd.set_option('display.width', None)
alt.data_transformers.disable_max_rows()

url = 'https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/googleplaystore.csv'
gps_apps_df = pd.read_csv(url)
gps_apps_df = gps_apps_df.dropna(subset = ['Content Rating', 'Category', 'Rating'])
selector = alt.selection_point(fields = ['Category'])

top_cat = gps_apps_df['Category'].value_counts().nlargest(8).index
top_8 = gps_apps_df[gps_apps_df['Category'].isin(top_cat)]
bar_chart = (
    alt.Chart(top_8, title = 'Top 8 Apps Categories')
    .mark_bar()
    .encode(
        x = alt.X('Category:N', title = 'App Category', sort = '-y'),
        y = alt.Y('count():Q', title = 'Number of Apps'),
        color = alt.condition(selector, alt.value('steelblue'), alt.value('lightgrey'))
    )
    .properties(width = 250, height = 300)
    .add_params(selector)
)

histogram = (
    alt.Chart(gps_apps_df, title = 'App Rating Distribution')
    .mark_bar()
    .encode(
        x = alt.X('Rating:Q', bin = True, title = 'App Rating'),
        y = alt.Y('count():Q', title = 'Number of Apps'),
        color = alt.Color('Type:N', title = 'Free or Paid')
    )
    .transform_filter(selector)
)

linked = bar_chart | histogram
linked.save('gps_category_ratings.html')
