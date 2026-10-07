import pandas as pd
import altair as alt

hpi_url = ("https://raw.githubusercontent.com/TrainingByPackt/Interactive-Data-Visualization-with-Python/master/datasets/hpi_data_countries.tsv")
hpi_df = pd.read_csv(hpi_url, sep = '\t')
alt.renderers.enable('default')

wb_slider = alt.param(
    name = 'wb_min',
    value = 5.0,
    bind = alt.binding_range(min = 0.0, max = 10.0, step = 0.1, name = "min Wellbeing: ")
)

chart = (
    alt.Chart(hpi_df, width = 700, height = 500)
    .mark_circle()
    .encode(
        x = 'Wellbeing (0-10)',
        y= 'Happy Planet Index',
        color = 'Region:N',
        size = alt.Size(
            'Ecological Footprint (gha/capita):Q',
            scale = alt.Scale(range = [50,1000]),
            title = 'Ecological Footprint (gha/capita)'
        ),
        tooltip = [
            'Country:N', 
            'Region:N', 
            'Wellbeing (0-10):Q', 
            'Happy Planet Index:Q'
        ]
    )
    .add_params(wb_slider)
    .transform_filter((alt.datum["Wellbeing (0-10)"] >= wb_slider))
    .interactive()
)

chart.show()
chart.save('lec15_ex01.html')
print("Saved lec15_ex01.html")

#Exercise 2

region_values = sorted(hpi_df["Region"].dropna().unique().tolist())
region_options = ['All'] + region_values

region_select = alt.param(
    name = 'region_pick',
    value = 'All',
    bind = alt.binding_select(options = region_options, name = 'Region: ')
)

chart = (
    alt.Chart(hpi_df, width = 900, height = 600)
    .mark_circle()
    .add_params(region_select)
    .encode(
        x = 'Wellbeing (0-10)',
        y= 'Happy Planet Index',
        color = 'Region',
        size = alt.Size(
                    'Ecological Footprint (gha/capita):Q',
                    scale = alt.Scale(range = [50,1000]),
                    title = 'Ecological Footprint (gha/capita)'
                ),
        tooltip = [
                    'Country:N', 
                    'Region:N', 
                    'Wellbeing (0-10):Q', 
                    'Happy Planet Index:Q'
                ]
    )
    .transform_filter((region_select == 'All') | (alt.datum.Region == region_select))
    .interactive()
)

chart.show()
chart.save('lec15_ex02.html')
print("Saved lec15_ex02.html")
