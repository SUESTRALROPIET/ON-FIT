<template>
  <div id="chart">
    <apexchart type="line" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ExRecord',
  props: {
    currentDate: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      month: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
      len: [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
      calData: undefined,
      timeData: undefined,
      series: [
        {
          name: '칼로리',
          type: 'line',
          data: [],
        },
        {
          name: '운동 시간',
          type: 'line',
          data: [],
        },
      ],
      chartOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false,
          },
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
          // TODO: category 파악하기
          categories: ['1/11/2000', '2/11/2000', '3/11/2000', '4/11/2000', '5/11/2000', '6/11/2000', '7/11/2000', '8/11/2000', '9/11/2000', '10/11/2000', '11/11/2000', '12/11/2000', '1/11/2001', '2/11/2001', '3/11/2001', '4/11/2001', '5/11/2001', '6/11/2001'],
          // labels: {
          //   formatter(value, timestamp, opts) {
          //     return opts.dateFormatter(new Date(timestamp), 'dd MMM');
          //   },
          // },
        },
        title: {
          text: '쓸까말까',
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
            text: '칼로리',
          },

        }, {
          opposite: true,
          title: {
            text: '운동 시간',
          },
        }],
      },
    };
  },
  created() {
    console.log('created');
  },
  updated() {
    console.log('차트페이지');
    console.log(this.currentDate);
  },
  beforeMount() {
    console.log('beforeMount');
  },
  mounted() {
    // TODO: 언제 불러와야 될까??
    console.log('mounted');
    this.getMonthlyLog();
  },
  methods: {
    // 운동한 날짜 다 가져오기
    convertDate(dateTime) {
      const result1 = dateTime.split('-');
      const result2 = result1.split('T');
      const ddmmyyyy = `${result2[0]}/${result1[1]}/${result1[0]}`;
      return ddmmyyyy;
    },
    getMonthlyLog() {
      // TODO: change userId
      console.log('이건 언제지');
      const userId = 1;
      axios.get(`http://localhost:8081/mypage/${userId}`)
        .then((res) => {
          // eslint-disable-next-line max-len
          const monthlyLog = res.data.MyExerciseLog.filter((v) => v.exTime.includes(this.currentDate));
          // 결과 저장할 배열
          console.log('여기가 중요');
          console.log(this.currentDate);
          const month = this.currentDate.substr(5);
          console.log(month);
          const leng = this.len.at(month);
          console.log(leng);
          this.series[0].data = new Array(leng).fill(0); // 칼로리
          this.series[1].data = new Array(leng).fill(0); // 시간
          this.series[0].data[0] = 0;
          console.log(this.series[0].data[0]);
          monthlyLog.forEach((v) => {
            console.log('여기?');
            const day = v.exTime.substr(8, 2) - 1;
            console.log('여기는?');
            if (this.series[0].data[day].isNaN) {
              this.series[0].data[day] = 0;
            }
            if (this.series[1].data[day].isNaN) {
              this.series[1].data[day] = 0;
            }
            this.series[0].data[day] += v.exCal;
            this.series[1].data[day] += v.exDuration;
          });
        });
    },
  },
};
</script>

<style scoped>
#chart >>> .apexcharts-toolbar {
  visibility: hidden !important;
}
</style>
