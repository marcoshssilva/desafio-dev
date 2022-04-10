import { Component, OnInit } from '@angular/core';
import { TransacaoService } from 'src/app/shared/services/transacao.service';

@Component({
  selector: 'app-upload-page',
  templateUrl: './upload-page.component.html',
  styleUrls: ['./upload-page.component.css'],
})
export class UploadPageComponent implements OnInit {
  file?: File;

  constructor(private transacaoService: TransacaoService) {}

  ngOnInit(): void {}

  onFileChoose(event: any) {
    this.file = event.target.files[0];
  }

  uploadFile() {
    if (this.file) {
      this.transacaoService
        .uploadFile(this.file)
        .subscribe((data) => console.log(data));
    }
  }
}
