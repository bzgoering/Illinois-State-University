import pandas as pd
import altair as alt
alt.data_transformers.disable_max_rows()

hpi_url = ('http://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/hpi_data_countries.tsv')
hpi_df = pd.read_csv(hpi_url, sep = '\t')

chart = (
    alt.Chart(hpi_df, title = 'Life Expectancy vs. Wellbeing by Region')
    .mark_circle()
    .encode(
        x = alt.X('Life Expectancy (years):Q', title = 'Life Expectancy (years)'),
        y = alt.Y('Wellbeing (0-10):Q', title = 'Wellbeing'),
        color = 'Region:N',
        tooltip = [
            alt.Tooltip('Region:N', title = 'Region'),
            alt.Tooltip('Life Expectancy (years):Q', title = 'Life Expectancy'),
            alt.Tooltip('count():Q', title = '# Countries'),
            alt.Tooltip('Wellbeing (0-10):Q', title = 'Wellbeing')
        ]
    )
    .properties(width = 600, height = 400)
    .interactive()
)

output_html = 'ex01.html'
chart.save(output_html)
region_order = ['Americas', 'Asia Pacific', 'Europe, Middle East and North Africa', 'Post-communist, Sub Saharan Africa']
bars_layer = (
    alt.Chart(hpi_df)
    .mark_bar()
    .encode(
        x = alt.X('Region:N', title = 'Region', sort = region_order),
        y = alt.Y('Wellbeing (0-10):Q', aggregate = 'mean', title = 'Mean Wellbeing'),
        tooltip = [
            alt.Tooltip('Region:N', title = 'Region'),
            alt.Tooltip('Wellbeing (0-10):Q', aggregate = 'mean', title = 'Mean Wellbeing'),
            alt.Tooltip('count():Q', title = '# Countries'),                    
        ]   
    )
    .properties(width = 500)
    .interactive()
)

line_layer = (
    alt.Chart(hpi_df)
    .mark_rule(color = 'red')
    .encode(
        y = alt.Y('Wellbeing (0-10):Q', aggregate = 'mean', title = 'Mean Wellbeing'),
        size = alt.SizeValue(3)
    )
)

#chart = alt.layer(bars_layer, line_layer, data = hpi_df).properties(title = 'Mean Happy Planet Index HPI by Region')
chart = (bars_layer + line_layer).properties(title = 'Mean Happy Planet Index HPI by Region')

output_html = 'ex02.html'
chart.save(output_html)
