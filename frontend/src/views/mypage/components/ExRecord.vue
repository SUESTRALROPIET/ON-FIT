<template>
  <div id="chart">
    <apexchart type="line" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
export default {
  name: 'ExRecord',
  data() {
    return {
      series: [
        {
          name: 'Website Blog',
          type: 'column',
          data: [440, 505, 414, 671, 227, 413, 201, 352, 752, 320, 257, 160],
        },
        {
          name: 'Sales',
          type: 'line',
          data: [4, 3, 10, 9, 29, 19, 22, 9, 12, 7, 19, 5, 13, 9, 17, 2, 7, 5],
        },
        {
          name: 'one More',
          type: 'line',
          data: [14, 33, 10, 39, 9, 69, 92, 59, 32, 7, 19, 5, 13, 9, 17, 2, 7, 5],
        },
      ],
      chartOptions: {
        chart: {
          height: 350,
          type: 'line',
        },
        colors: ['#F4F10E', '#0E73F4', '#61D081'],
        // forecastDataPoints: {
        //   count: 7,
        // },
        stroke: {
          width: 3,
          curve: 'smooth',
        },
        xaxis: {
          type: 'datetime',
          categories: ['1/11/2000', '2/11/2000', '3/11/2000', '4/11/2000', '5/11/2000', '6/11/2000', '7/11/2000', '8/11/2000', '9/11/2000', '10/11/2000', '11/11/2000', '12/11/2000', '1/11/2001', '2/11/2001', '3/11/2001', '4/11/2001', '5/11/2001', '6/11/2001'],
          // labels: {
          //   formatter(value, timestamp, opts) {
          //     return opts.dateFormatter(new Date(timestamp), 'dd MMM');
          //   },
          // },
        },
        title: {
          text: 'Forecast',
          align: 'left',
          style: {
            fontSize: '16px',
            color: '#666',
          },
        },
        // fill: {
        //   type: 'gradient',
        //   gradient: {
        //     // shade: 'dark',
        //     gradientToColors: ['#FDD835'],
        //     // shadeIntensity: 2,
        //     type: 'horizontal',
        //     // opacityFrom: 1,
        //     // opacityTo: 1,
        //     stops: [0, 100, 100, 100],
        //   },
        // },
        yaxis: [{
          title: {
            text: 'Website Blog',
          },

        }, {
          opposite: true,
          title: {
            text: 'Social Media',
          },
        }],
      },
    };
  },
};
</script>

<style scoped>
#chart >>> .apexcharts-toolbar {
  visibility: hidden !important;
}
</style>
