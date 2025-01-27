import { Component, OnInit } from '@angular/core';
import * as bootstrap from 'bootstrap';
import { TransacaoService } from 'src/app/shared/services/transacao.service';

@Component({
  selector: 'app-upload-page',
  templateUrl: './upload-page.component.html',
  styleUrls: ['./upload-page.component.css'],
})
export class UploadPageComponent implements OnInit {
  file?: File;
  error: boolean = false
  errorMessage:string = ""

  constructor(private transacaoService: TransacaoService) {}

  ngOnInit(): void {}

  onFileChoose(event: any) {
    this.file = event.target.files[0];
    this.error = false
  }

  uploadFile() {
    if (this.file) {
      this.transacaoService.uploadFile(this.file)
        .subscribe(() => this.printMessage(),
          err => {
            this.error = true
            this.errorMessage = err.error.error
          }
        );
    }
  }

  printMessage() {
    var toastElement = document.getElementById('uploadPage_toastMessage');
    var toast = new bootstrap.Toast(toastElement as Element);
    toast.show();
  }
}
